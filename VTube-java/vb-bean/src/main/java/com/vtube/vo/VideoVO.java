package com.vtube.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vtube.domain.VideoEpisode;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Data
public class VideoVO {
    private Integer videoId;
    private String title;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private String categoryName;
    private Integer categoryId;
    private String videoPath;
    private String imagePath;
    private int views;
    private int likes;
    private String nickName;
    private Integer userId;
    private String tags;
    private Integer type;
    private List<String> tagsList;

    private List<VideoEpisode> episodeList;


    public void setTags(String tags) {
        this.tags = tags;
        this.tagsList = Arrays.asList(tags.split(","));
    }
}

