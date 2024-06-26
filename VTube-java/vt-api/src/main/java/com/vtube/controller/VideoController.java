package com.vtube.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.dto.VideoDTO;
import com.vtube.dto.VideoSummaryDTO;
import com.vtube.mapper.VideoMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "首页内容")
@RequestMapping("/video")
public class VideoController {
    @Resource
    private VideoService videoService;

    @Resource
    private VideoMapper videoMapper;


    @GetMapping("/LatestUpdate")
    ApiResult LatestUpdate() {
        return ApiResult.ok(videoMapper.selectLatestVideos8());
    }

    @GetMapping("/hot")
    ApiResult hot(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                  @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize) {
        Page<VideoDTO> page = new Page<>(pageNum, pageSize);
        return ApiResult.ok(videoMapper.selectTopPlayedVideos(page));
    }
    @PostMapping("/details")
    @Operation(summary = "视频详情", description = "视频详情")
    ApiResult details(@RequestBody JSONObject jsonObject) {
        String videoId = jsonObject.getStr("videoId");
        return ApiResult.ok(videoMapper.getVideoDetails2(Integer.valueOf(videoId)));
    }
}
