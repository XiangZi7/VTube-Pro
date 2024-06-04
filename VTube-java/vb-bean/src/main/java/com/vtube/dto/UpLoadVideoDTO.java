package com.vtube.dto;

import com.vtube.domain.VideoEpisode;
import lombok.Data;

import java.util.List;

@Data
public class UpLoadVideoDTO {
    private Integer videoId;
    private String title;
    private String description;
    private String imagePath;
    private String tags;
    private String type;
    private String categoryId;
    private List<VideoEpisode> episodeList;
}


