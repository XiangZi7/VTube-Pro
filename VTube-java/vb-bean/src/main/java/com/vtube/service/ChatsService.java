package com.vtube.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vtube.domain.Chats;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vtube.domain.Messages;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @description 针对表【vt_chats(聊天会话表)】的数据库操作Service
 * @createDate 2024-06-06 16:41:27
 */
public interface ChatsService extends IService<Chats> {
    Optional<Chats> getChatById(String chatId);
}
