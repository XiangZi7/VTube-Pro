package com.vtube.vo;

import lombok.Data;

@Data
public class VideoVO {
    private Integer videoId;
    private String title;
    private String description;
    private String createTime;
    private String categoryName;
    private Integer categoryId;
    private String videoPath;
    private String imagePath;
    private int views;
    private int likes;
    private String userName;
    private Integer userId;
}
