package com.vtube.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.vtube.domain.User;
import com.vtube.model.ApiResult;
import com.vtube.service.UserService;
import com.vtube.vo.Param.LoginParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "登录")
public class LoginController {

    @Resource
    private UserService userService;


    @PostMapping("/login")
    ApiResult login(@RequestBody LoginParam loginParam) {
        return userService.Login(loginParam);
    }

    @PostMapping("/SignIn")
    ApiResult SignIn(@RequestBody User user) {
        return userService.SignIn(user);
    }

    @PostMapping("/logout")
    ApiResult logout() {
        StpUtil.logout();
        return ApiResult.ok("退出成功");
    }
}
