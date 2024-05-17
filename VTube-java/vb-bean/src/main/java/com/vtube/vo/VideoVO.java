package com.vtube.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

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
    private String userName;
    private Integer userId;
}
