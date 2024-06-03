package com.vtube.dto;

import lombok.Data;

@Data
public class VideoSummaryDTO {
    private Integer videoId;
    private String title;
    private String imagePath;
    private String createTime;
    private Integer views;
    private Integer likes;
}