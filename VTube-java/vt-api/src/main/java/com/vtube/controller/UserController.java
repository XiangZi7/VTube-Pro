package com.vtube.controller;

import com.vtube.domain.User;
import com.vtube.model.ApiResult;
import com.vtube.service.UserService;
import com.vtube.vo.Param.PassWordParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "用户")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/edit")
    @Operation(summary = "编辑用户个人信息", description = "编辑用户个人信息")
    ApiResult edit(User user) {
        return ApiResult.ok(userService.updateById(user));
    }
    @PostMapping("/changePwd")
    @Operation(summary = "密码修改", description = "密码修改")
    ApiResult changePwd(@RequestBody PassWordParam passWordParam) {
        return userService.ChangePwd(passWordParam);
    }
}
