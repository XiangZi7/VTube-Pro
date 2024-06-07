package com.vtube.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Comments;
import com.vtube.dto.CommentDTO;
import com.vtube.model.ApiResult;
import com.vtube.service.CommentsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentsController {
    @Resource
    private CommentsService commentsService;

    @GetMapping("/comments")
    ApiResult comments(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                       @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, Integer targetType, Integer videoId) {
        Page<CommentDTO> page = new Page<>(pageNum, pageSize);
        return ApiResult.ok(commentsService.getPagedCommentsWithChildren(page, targetType, videoId));
    }
    @PostMapping("/send")
    ApiResult send(@RequestBody Comments comments) {
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        System.out.println("loginIdAsLong=>" + loginIdAsLong);
        comments.setUserId((int) loginIdAsLong);
        commentsService.save(comments);
        return ApiResult.ok("评论成功");
    }

}
