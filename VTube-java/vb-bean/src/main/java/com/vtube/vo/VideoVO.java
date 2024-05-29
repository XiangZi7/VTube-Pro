package com.vtube.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vtube.domain.VideoEpisode;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class VideoVO {
    @ExcelProperty("videoId")
    private Integer videoId;
    @ExcelProperty("title")
    private String title;
    @ExcelProperty("description")
    private String description;
    @ExcelProperty("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @ExcelProperty("categoryName")
    private String categoryName;
    @ExcelProperty("categoryId")
    private Integer categoryId;
    @ExcelProperty("videoPath")
    private String videoPath;
    @ExcelProperty("imagePath")
    private String imagePath;
    @ExcelProperty("views")
    private int views;
    @ExcelProperty("likes")
    private int likes;
    @ExcelProperty("nickName")
    private String nickName;
    @ExcelProperty("userId")
    private Integer userId;
    @ExcelProperty("tags")
    private String tags;
    @ExcelProperty("type")
    private Integer type;

    private LocalDate releaseTime;
    private List<String> tagsList;

    private List<VideoEpisode> episodeList;


    public void setTags(String tags) {
        this.tags = tags;
        this.tagsList = Arrays.asList(tags.split(","));
    }
}

