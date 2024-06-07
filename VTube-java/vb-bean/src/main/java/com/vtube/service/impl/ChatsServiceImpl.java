package com.vtube.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Chats;
import com.vtube.domain.Messages;
import com.vtube.mapper.MessagesMapper;
import com.vtube.service.ChatsService;
import com.vtube.mapper.ChatsMapper;
import com.vtube.service.MessagesService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【vt_chats(聊天会话表)】的数据库操作Service实现
 * @createDate 2024-06-06 16:41:27
 */
@Service
public class ChatsServiceImpl extends ServiceImpl<ChatsMapper, Chats>
        implements ChatsService {

    @Resource
    private MessagesMapper messagesMapper;

    @Resource
    private MessagesService messagesService;

    @Resource
    private ChatsMapper chatsMapper;

    public void saveMessage(String chatId, int senderId, String content) {
        Messages message = new Messages();
        message.setChatId(chatId);
        message.setSenderId(String.valueOf(senderId));
        message.setContent(content);
        messagesMapper.insert(message);
    }

    public List<Messages> getMessages(String chatId) {
        QueryWrapper<Messages> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chat_id", chatId);
        return messagesMapper.selectList(queryWrapper);
    }
}




