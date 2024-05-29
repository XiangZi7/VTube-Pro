package com.vtube.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Video;
import com.vtube.dto.ProjectorDTO;
import com.vtube.mapper.VideoMapper;
import com.vtube.model.ApiResult;
import com.vtube.vo.Param.VideoParam;
import com.vtube.vo.VideoVO;
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
@Tag(name = "番组计划")
@RequestMapping("/Projector")
public class ProjectorController {
    @Resource
    private VideoMapper videoMapper;


    @GetMapping("/list")
    @Operation(summary = "番组计划列表", description = "番组计划列表")
    ApiResult list(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                   @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, ProjectorDTO videoVOParam) {
        Page<Video> page = new Page<>(pageNum, pageSize);
        if (videoVOParam.getTags() != null) {
            videoVOParam.setTags(videoVOParam.getTags());
        }
        IPage<ProjectorDTO> videoIPage = videoMapper.ProjectorList(page, videoVOParam);

        videoIPage.getRecords().stream().forEach(item -> {
            if (item.getTags() != null) {
                item.setTags(item.getTags());
            }
        });
        return ApiResult.ok(videoIPage);
    }
}
