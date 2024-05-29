package com.vtube.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ProjectorDTO {
    private Integer videoId;
    private String title;
    private String releaseTime;
    private String imagePath;
    private Integer views;
    private Integer likes;
    private Integer categoryId;
    private String tags;
    private List<String> tagsList;
    private String type;
    private Double make;
    private String categoryName;
    public void setTags(String tags) {
        this.tags = tags;
        this.tagsList = Arrays.asList(tags.split(","));
    }
}
