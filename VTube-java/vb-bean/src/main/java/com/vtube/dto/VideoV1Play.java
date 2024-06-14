package com.vtube.dto;

import lombok.Data;

import java.util.List;


@Data
public class VideoV1Play {
    private List<VideoPlay> VideoPlays;
    private Integer episodesCount;

    @Data
    public static class VideoPlay {
        private String playData;
        private String srcSite;
    }
}