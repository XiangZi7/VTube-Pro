package com.vtube.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Video;
import com.vtube.mapper.VideoMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.VideoService;
import com.vtube.vo.Param.VideoVOParam;
import com.vtube.vo.VideoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
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

    @GetMapping("/list")
    @Operation(summary = "视频列表", description = "视频列表")
    public ApiResult<?> list(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                             @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, VideoVOParam videoVOParam) {
        Page<Video> page = new Page<>(pageNum, pageSize);
        IPage<VideoVO> videoIPage = videoMapper.VideoList(page, videoVOParam);
        return ApiResult.ok(videoIPage);
    }

    @PostMapping("/add")
    @Operation(summary = "视频添加", description = "视频添加")
    public ApiResult<?> add(@RequestBody VideoVO video) {
        videoService.addVideoData(video);
        return ApiResult.ok("新增成功");
    }

    @PostMapping("/edit")
    @Operation(summary = "视频更新", description = "视频更新")
    public ApiResult<?> edit(@RequestBody VideoVO video) {
        videoService.updateVideoData(video);
        return ApiResult.ok("视频更新成功");
    }

    @PostMapping("/deletes")
    @Operation(summary = "视频删除", description = "视频删除")
    public ApiResult<?> deletes(@RequestBody List<Integer> ids) {
        videoMapper.deleteBatchIds(ids);
        return ApiResult.ok("删除成功");
    }
}
