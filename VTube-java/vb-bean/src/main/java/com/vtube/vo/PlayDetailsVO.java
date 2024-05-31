package com.vtube.vo;

import lombok.Data;

@Data
public class PlayDetailsVO {
    private Integer videoId;
    private String title;
    private String description;
    private String createTime;
    private String tags;
    private String imagePath;
    private String releaseTime;
    private Integer views;
    private double likes;
    private double make;
}
