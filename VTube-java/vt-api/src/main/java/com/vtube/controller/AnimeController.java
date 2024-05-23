package com.vtube.controller;

import com.vtube.model.ApiResult;
import com.vtube.service.VideoUpdateService;
import com.vtube.vo.WeeklyAnimeUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Tag(name = "动漫首页")
public class AnimeController {

    @Resource
    private VideoUpdateService videoUpdateService;
    @GetMapping("/weekly-anime")
    @Operation(summary = "番剧更新表", description = "番剧更新表")
    public ApiResult<?> logout() {
        Map<String, List<WeeklyAnimeUpdateVO>> weeklyAnimeUpdates = videoUpdateService.getWeeklyAnimeUpdates();
        // 创建一个包含七个子数组的列表，分别对应周一到周日
        List<List<WeeklyAnimeUpdateVO>> weeklyUpdatesList = new ArrayList<>(7);
        for (int i = 1; i <= 7; i++) {
            weeklyUpdatesList.add(weeklyAnimeUpdates.get(String.valueOf(i)));
        }
        return ApiResult.ok(weeklyUpdatesList);
    }
}
