package com.vtube.controller;

import com.vtube.dto.VideoDTO;
import com.vtube.mapper.VideoMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.VideoUpdateService;
import com.vtube.vo.WeeklyAnimeUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Tag(name = "新番放送")
@RequestMapping("/anime")
public class AnimeController {

    @Resource
    private VideoUpdateService videoUpdateService;
    @Resource
    private VideoMapper videoMapper;

    @GetMapping("/weekly-anime")
    @Operation(summary = "番剧更新表", description = "番剧更新表")
    ApiResult logout() {
        Map<String, List<WeeklyAnimeUpdateVO>> weeklyAnimeUpdates = videoUpdateService.getWeeklyAnimeUpdates();
        // 创建一个包含七个子数组的列表，分别对应周一到周日
        List<List<WeeklyAnimeUpdateVO>> weeklyUpdatesList = new ArrayList<>(7);
        for (int i = 1; i <= 7; i++) {
            weeklyUpdatesList.add(weeklyAnimeUpdates.get(String.valueOf(i)));
        }
        return ApiResult.ok(weeklyUpdatesList);
    }

    @GetMapping("/hot")
    @Operation(summary = "热门&推荐", description = "热门&推荐")
    ApiResult hot() {
        List<VideoDTO> videoDTOS = videoMapper.PCTOP10();
        List<VideoDTO> recommendedVideos = videoMapper.findRecommendedVideos();

        HashMap<String, Object> map = new HashMap<>();
        map.put("top10", videoDTOS);
        map.put("recommended", recommendedVideos);
        return ApiResult.ok(map);
    }
}
