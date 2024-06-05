package com.vtube.controller;

import cn.hutool.json.JSONObject;
import com.vtube.model.ApiResult;
import com.vtube.service.CommentsService;
import com.vtube.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "播放")
@RequestMapping("/play")
public class playController {

    @Resource
    private VideoService videoService;

    @Resource
    private CommentsService commentsService;

    @PostMapping("/details")
    @Operation(summary = "视频详情", description = "视频详情")
    ApiResult details(@RequestBody JSONObject jsonObject) {
        String videoId = jsonObject.getStr("videoId");
        return ApiResult.ok(videoService.Details(Integer.valueOf(videoId)));
    }

    @PostMapping("/episode")
    @Operation(summary = "视频集数", description = "视频集数")
    ApiResult episode(@RequestBody JSONObject jsonObject) {
        String videoId = jsonObject.getStr("videoId");
        return ApiResult.ok(videoService.Episode(Integer.valueOf(videoId)));
    }

}
