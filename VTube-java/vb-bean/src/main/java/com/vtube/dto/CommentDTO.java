package com.vtube.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDTO {
    private Integer commentId;
    private String content;
    private String nickName;
    private Integer userId;
    private String avatarPath;
    private Integer parentId;
    private LocalDateTime createTime;
    private Integer status;
    private List<CommentDTO> children;  // 子评论
}
