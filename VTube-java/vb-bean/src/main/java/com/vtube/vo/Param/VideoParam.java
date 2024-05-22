package com.vtube.vo.Param;

import lombok.Data;
import java.util.List;

@Data
public class VideoParam {
    private Integer userId;
    private Integer categoryId;
    private Integer type;
    private String title;
    private String createTime;
    private List<String> tags;
}
