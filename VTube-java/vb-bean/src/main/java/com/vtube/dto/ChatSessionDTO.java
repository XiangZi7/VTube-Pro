package com.vtube.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatSessionDTO {
    private Long userId;
    private String nickName;
    private String avatarPath;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private String chatId;

}