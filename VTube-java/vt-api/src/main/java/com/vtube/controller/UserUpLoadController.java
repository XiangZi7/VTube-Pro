package com.vtube.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Video;
import com.vtube.domain.VideoEpisode;
import com.vtube.dto.CommentDTO;
import com.vtube.dto.UserLoginDTO;
import com.vtube.dto.VideoSummaryDTO;
import com.vtube.mapper.VideoMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.VideoService;
import com.vtube.service.impl.VideoServiceImpl;
import com.vtube.vo.Param.VideoUpdateParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manuscript")
@Tag(name = "用户稿件")
public class UserUpLoadController {
    @Resource
    private VideoService videoService;
    @Resource
    private VideoMapper videoMapper;
    @Resource
    private VideoServiceImpl videoService2;

    @GetMapping("/VideoManagementList")
    @Operation(summary = "上传视频稿件", description = "上传视频稿件")
    ApiResult VideoManagementList(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, String title) {
        UserLoginDTO user = (UserLoginDTO) StpUtil.getSession().get("user");
        Page<VideoSummaryDTO> page = new Page<>(pageNum, pageSize);
        return ApiResult.ok(videoMapper.VideoManagementList(page, user.getUserId(), title));
    }

    @PostMapping("/addVideo")
    @Operation(summary = "上传视频稿件", description = "上传视频稿件")
    ApiResult addVideo(@RequestBody VideoUpdateParams video) {
        videoService2.addVideo(video);
        return ApiResult.ok("投稿成功");
    }

    @PostMapping("/editVideo")
    @Operation(summary = "编辑视频稿件", description = "编辑视频稿件")
    ApiResult editVideo(@RequestBody VideoUpdateParams video) {
        videoService2.updateVideo(video);
        return ApiResult.ok("更新成功");
    }

    @PostMapping("/deleteVideo")
    @Operation(summary = "删除视频稿件", description = "删除视频稿件")
    ApiResult deleteVideo(@RequestBody Video video) {
        return ApiResult.ok(videoService.updateById(video));
    }
    @PostMapping("/queryByVideoId")
    @Operation(summary = "根据视频Id查找稿件", description = "根据视频Id查找稿件")
    ApiResult queryByVideoId(@RequestBody JSONObject jsonObject) {
        String videoId = jsonObject.getStr("videoId");
        return videoService.queryByVideoId(Integer.valueOf(videoId));
    }
}
