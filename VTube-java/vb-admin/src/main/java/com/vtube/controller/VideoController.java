package com.vtube.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Video;
import com.vtube.mapper.VideoMapper;
import com.vtube.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "视频管理")
@RequestMapping("/video")
public class VideoController {

    @Resource
    private VideoMapper videoMapper;

    @GetMapping("/list")
    @Operation(summary = "视频列表", description = "视频列表")
    public ApiResult<?> list(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                             @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize) {
        Page<Video> page = new Page<>(pageNum, pageSize);
        IPage<Video> videoIPage = videoMapper.VideoList(page);
        return ApiResult.ok(videoIPage);
    }
}
