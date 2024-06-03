package com.vtube.vo.Param;

import com.vtube.domain.Video;
import com.vtube.domain.VideoEpisode;
import lombok.Data;

import java.util.List;
@Data
public class VideoUpdateParams {
    private Video video;
    private Integer categoryId;
    private List<VideoEpisode> episodeList;
}
