package com.vtube.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Video;
import com.vtube.dto.CommentDTO;
import com.vtube.model.ApiResult;
import com.vtube.service.CommentsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController {
    @Resource
    private CommentsService commentsService;

    @GetMapping("comments")
    ApiResult comments(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                       @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, Integer targetType, Integer videoId) {
        Page<CommentDTO> page = new Page<>(pageNum, pageSize);
        return ApiResult.ok(commentsService.getPagedCommentsWithChildren(page, targetType, videoId));
    }
}
