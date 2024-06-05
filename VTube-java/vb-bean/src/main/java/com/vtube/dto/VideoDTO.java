package com.vtube.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VideoDTO {
    private int videoId;
    private String title;
    private Date createTime;
    private String imagePath;
    private String nickName;
    private String avatarPath;
    private int views;
    private int likes;
}
