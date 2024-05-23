package com.vtube.controller;

import cn.hutool.json.JSONObject;
import com.vtube.model.ApiResult;
import com.vtube.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "播放")
@RequestMapping("/play")
public class playController {

    @Resource
    private VideoService videoService;

    @PostMapping("/details")
    @Operation(summary = "视频详情", description = "视频详情")
    public ApiResult<?> details(@RequestBody JSONObject jsonObject) {
        String videoId = jsonObject.getStr("videoId");
        return ApiResult.ok(videoService.Details(Integer.valueOf(videoId)));
    }

    @PostMapping("/episode")
    @Operation(summary = "视频集数", description = "视频集数")
    public ApiResult<?> episode(@RequestBody JSONObject jsonObject) {
        String videoId = jsonObject.getStr("videoId");
        return ApiResult.ok(videoService.Episode(Integer.valueOf(videoId)));
    }
}
