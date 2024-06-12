package com.vtube.netty.handler;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vtube.domain.Chats;
import com.vtube.domain.Messages;
import com.vtube.mapper.ChatsMapper;
import com.vtube.service.MessagesService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ChannelHandler.Sharable
@Component
public class NettyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Autowired
    private ChatsMapper chatsMapper;

    @Autowired
    private MessagesService messagesService;

    // 存储用户和会话的映射
    private static ConcurrentMap<String, Channel> userChannelMap = new ConcurrentHashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String message = msg.text();

        // 过滤心跳检测消息
        if ("ping".equals(message)) {
            // 回应心跳
            ctx.channel().writeAndFlush(new TextWebSocketFrame(""));
            return;
        }

        // 解析消息
        JsonNode jsonNode = objectMapper.readTree(message);

        String sender = jsonNode.get("sender").asText();
        String recipient = jsonNode.get("recipient").asText();
        String content = jsonNode.get("content").asText();

        // 保存或更新用户的会话
        userChannelMap.put(sender, ctx.channel());

        // 确保聊天会话存在
        String chat = chatsMapper.getChatId(sender, recipient);

        if (chat == null) {
            Chats chats = new Chats();
            chats.setChatId(IdUtil.simpleUUID());
            chats.setParticipantOne(sender);
            chats.setParticipantTwo(recipient);
            chatsMapper.insert(chats);
            chat = chats.getChatId();
        }


        // 保存消息
        Messages messages = new Messages();
        messages.setChatId(chat);
        messages.setContent(content);
        messages.setSenderId(sender);
        messagesService.save(messages);

        // 查找接收者的会话
        Channel recipientChannel = userChannelMap.get(recipient);

        if (recipientChannel != null) {
            // 转发私聊消息到接收者
            recipientChannel.writeAndFlush(new TextWebSocketFrame(content));
        } else {
            // 处理接收者不在线的情况（选择性的实现）
            ctx.channel().writeAndFlush(new TextWebSocketFrame("Recipient not online"));
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 处理新连接的事件
        Channel incoming = ctx.channel();
        System.out.println("User joined - " + incoming.remoteAddress());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 处理连接断开的事件
        Channel incoming = ctx.channel();
        System.out.println("User left - " + incoming.remoteAddress());
        // 删除用户会话
        userChannelMap.values().removeIf(channel -> channel == ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 处理异常
        cause.printStackTrace();
        ctx.close();
    }
}