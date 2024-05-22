package com.vtube.controller;

import com.vtube.model.ApiResult;
import com.vtube.service.VideoUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@Tag(name = "动漫首页")
public class AnimeController {

    @Resource
    private VideoUpdateService videoUpdateService;
    @GetMapping("/weekly-anime")
    @Operation(summary = "番剧更新表", description = "番剧更新表")
    public ApiResult<?> logout() {
        return ApiResult.ok(videoUpdateService.getWeeklyAnimeUpdates());
    }
}
