package com.vtube.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Video;
import com.vtube.mapper.VideoMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.VideoService;
import com.vtube.vo.Param.VideoParam;
import com.vtube.vo.VideoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "审核")
@RequestMapping("/auditing")
public class AuditingController {

    @Resource
    private VideoService videoService;

    @Resource
    private VideoMapper videoMapper;

    @GetMapping("/list")
    @Operation(summary = "审核列表", description = "审核列表")
    ApiResult list(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                   @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, VideoParam videoVOParam) {
        Page<Video> page = new Page<>(pageNum, pageSize);
        IPage<VideoVO> videoIPage = videoMapper.VideoAuditingList(page, videoVOParam);
        return ApiResult.ok(videoIPage);
    }

    @PostMapping("/task")
    @Operation(summary = "编辑审核状态", description = "编辑审核状态")
    public ApiResult task(@RequestBody JSONObject jsonObject) {
        String videoId = jsonObject.getStr("videoId");
        String status = jsonObject.getStr("status");
        String remark = jsonObject.getStr("remark");

        // 创建 UpdateWrapper 并设置更新条件和更新内容
        UpdateWrapper<Video> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("video_id", videoId).set("review_status", status);

        // 如果是驳回给出理由
        if (status.equals("3")) {
            updateWrapper.set("remark", remark);
        }

        // 执行更新操作
        videoService.update(updateWrapper);

        // 判断状态并返回对应的信息
        String resultMessage = status.equals("2") ? "审核通过" : "审核驳回";

        return ApiResult.ok(resultMessage);
    }
}
