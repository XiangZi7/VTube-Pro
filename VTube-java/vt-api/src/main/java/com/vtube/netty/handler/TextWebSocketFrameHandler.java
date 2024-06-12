package com.vtube.netty.handler;

import com.vtube.service.MessagesService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Autowired
    private MessagesService messageService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        String receivedText = msg.text();
        System.out.println("receivedText=>>>" + receivedText);
        // 处理接收到的消息
        // 想将消息广播给其他所有连接的客户端
        ctx.channel().writeAndFlush(new TextWebSocketFrame(receivedText));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 有新的连接加入时调用
        System.out.println("Client " + ctx.channel().id().asLongText() + " added");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 有连接断开时调用
        System.out.println("Client " + ctx.channel().id().asLongText() + " removed");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}