package com.vtube.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Video;
import com.vtube.domain.VideoEpisode;
import com.vtube.domain.VideoUpdate;
import com.vtube.mapper.VideoMapper;
import com.vtube.mapper.VideoUpdateMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.VideoEpisodeService;
import com.vtube.service.VideoService;
import com.vtube.vo.Param.VideoParam;
import com.vtube.vo.VideoVO;
import com.vtube.vo.WeeklyAnimeUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "视频管理")
@RequestMapping("/video")
public class VideoController {

    @Resource
    private VideoMapper videoMapper;
    @Resource
    private VideoService videoService;
    @Resource
    private VideoEpisodeService episodeService;
    @Resource
    private VideoUpdateMapper videoUpdateMapper;

    @GetMapping("/list")
    @Operation(summary = "视频列表", description = "视频列表")
    ApiResult list(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                   @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, VideoParam videoVOParam) {
        Page<Video> page = new Page<>(pageNum, pageSize);
        IPage<VideoVO> videoIPage = videoMapper.VideoList(page, videoVOParam);

        videoIPage.getRecords().stream().forEach(item -> {
            if (item.getTags() != null) {
                item.setTags(item.getTags());
            }
        });
        return ApiResult.ok(videoIPage);
    }

    @PostMapping("/sublist")
    @Operation(summary = "视频集数", description = "视频集数")
    ApiResult sublist(@RequestBody VideoVO video) {
        QueryWrapper<VideoEpisode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id", video.getVideoId());
        queryWrapper.orderByAsc("episode_number");
        List<VideoEpisode> list = episodeService.list(queryWrapper);
        return ApiResult.ok(list);
    }

    @SaCheckRole("Super-Admin")
    @PostMapping("/add")
    @Operation(summary = "视频添加", description = "视频添加")
    ApiResult add(@RequestBody VideoVO video) {
        videoService.addVideoData(video);
        return ApiResult.ok("新增成功");
    }

    @SaCheckRole("Super-Admin")
    @PostMapping("/edit")
    @Operation(summary = "视频更新", description = "视频更新")
    ApiResult edit(@RequestBody VideoVO video) {
        videoService.updateVideoData(video);
        return ApiResult.ok("视频更新成功");
    }

    @SaCheckRole("Super-Admin")
    @PostMapping("/deletes")
    @Operation(summary = "视频删除", description = "视频删除")
    ApiResult deletes(@RequestBody List<Integer> ids) {
        videoMapper.deleteBatchIds(ids);
        return ApiResult.ok("删除成功");
    }

    //    ************* 周更新 ***************
    @Operation(summary = "周番列表", description = "周番列表")
    @GetMapping("/weekList")
    ApiResult weekList(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                       @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, WeeklyAnimeUpdateVO weeklyAnimeUpdateVO) {
        Page<Video> page = new Page<>(pageNum, pageSize);
        return ApiResult.ok(videoMapper.weekList(weeklyAnimeUpdateVO, page));
    }

    @PostMapping("/weekAdd")
    @Operation(summary = "周番添加", description = "周番添加")
    ApiResult weekAdd(@RequestBody VideoUpdate video) {
        return ApiResult.ok(videoUpdateMapper.insert(video));
    }

    @PostMapping("/weekEdit")
    @Operation(summary = "周番更新", description = "周番更新")
    ApiResult weekEdit(@RequestBody VideoUpdate video) {
        return ApiResult.ok(videoUpdateMapper.updateById(video));
    }

    @PostMapping("/weekDeletes")
    @Operation(summary = "周番删除", description = "周番删除")
    ApiResult weekDeletes(@RequestBody List<Integer> ids) {
        return ApiResult.ok(videoUpdateMapper.deleteBatchIds(ids));
    }
    @GetMapping("/weekIsNullList")
    @Operation(summary = "查出周番更新表没有的动漫", description = "查出周番更新表没有的动漫")
    ApiResult weekIsNullList(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                   @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, VideoParam videoVOParam) {
        Page<Video> page = new Page<>(pageNum, pageSize);
        IPage<VideoVO> videoIPage = videoMapper.VideoByWeekIsNullList(page, videoVOParam);
        videoIPage.getRecords().stream().forEach(item -> {
            if (item.getTags() != null) {
                item.setTags(item.getTags());
            }
        });
        return ApiResult.ok(videoIPage);
    }
}
