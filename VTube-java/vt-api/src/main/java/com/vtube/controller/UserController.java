package com.vtube.controller;

import com.vtube.domain.User;
import com.vtube.model.ApiResult;
import com.vtube.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Tag(name = "用户")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/edit")
    ApiResult edit(User user) {
        return ApiResult.ok(userService.updateById(user));
    }
}
