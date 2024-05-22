package com.vtube.vo;

import lombok.Data;

@Data
public class WeeklyAnimeUpdateVO {
    private Integer videoId;
    private String title;
    private String imagePath;
    private Integer weekDay;
}
