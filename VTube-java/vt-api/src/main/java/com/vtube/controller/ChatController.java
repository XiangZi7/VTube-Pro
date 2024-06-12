package com.vtube.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Chats;
import com.vtube.domain.Messages;
import com.vtube.dto.ChatSessionDTO;
import com.vtube.mapper.MessagesMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.ChatsService;
import com.vtube.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private MessagesService messagesService;

    @Autowired
    private ChatsService chatsService;

    @Autowired
    private MessagesMapper messagesMapper;

    @GetMapping("/list")
    ApiResult list(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                   @RequestParam(name = "pageSize", defaultValue = "20") Long pageSize) {
        Page<ChatSessionDTO> page = new Page<>(pageNum, pageSize);
        String loginIdAsString = StpUtil.getLoginIdAsString();

        IPage<ChatSessionDTO> chatSessionsByUserId = messagesMapper.getChatSessionsByUserId(page, Long.valueOf(loginIdAsString));
        return ApiResult.ok(chatSessionsByUserId);
    }


    @GetMapping("/comments")
    ApiResult comments(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                       @RequestParam(name = "pageSize", defaultValue = "20") Long pageSize, String chatId) {
        Page<Messages> page = new Page<>(pageNum, pageSize);
        return ApiResult.ok(messagesService.page(page, new QueryWrapper<Messages>().eq("chat_id",chatId).orderByDesc("timestamp")));
    }
}
