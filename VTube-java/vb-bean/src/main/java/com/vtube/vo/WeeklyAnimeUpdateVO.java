package com.vtube.vo;

import lombok.Data;

@Data
public class WeeklyAnimeUpdateVO {
    private Integer videoId;
    private String title;
    private Integer views;
    private String imagePath;
    private Integer weekDay;
    private Integer updateId;
}
