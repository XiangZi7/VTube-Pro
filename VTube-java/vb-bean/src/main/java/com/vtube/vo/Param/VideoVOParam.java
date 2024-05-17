package com.vtube.vo.Param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class VideoVOParam {
    private Integer userId;
    private Integer categoryId;
    private String title;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createTime;
}
