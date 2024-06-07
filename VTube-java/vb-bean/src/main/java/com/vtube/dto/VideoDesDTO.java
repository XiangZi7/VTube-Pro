package com.vtube.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDesDTO {
    private Long videoId;            // 视频 ID
    private String title;            // 标题
    private String description;      // 描述
    private Date createTime;         // 创建时间
    private List<String> tags;       // 标签列表
    private String nickName;         // 用户昵称
    private String userId;           // 用户ID
    private String avatarPath;       // 头像路径
    private String imagePath;        // 封面路径
    private Long views;              // 观看次数
    private Long likes;              // 点赞次数
    private String signature;        // 个性签名
}
