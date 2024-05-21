package com.vtube.vo.Param;

import com.vtube.domain.VideoEpisode;
import lombok.Data;
import java.util.List;

@Data
public class VideoVOParam {
    private Integer userId;
    private Integer categoryId;
    private Integer type;
    private String title;
    private String createTime;
    private List<String> tags;
}
