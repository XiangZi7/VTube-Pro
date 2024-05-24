package com.vtube.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.vtube.model.ApiResult;
import com.vtube.service.AdminLoginService;
import com.vtube.vo.Param.LoginParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "登录")
public class loginController {

    @Resource
    private AdminLoginService adminLoginService;

    @PostMapping("/login")
    @Operation(summary = "管理员登录", description = "管理员登录")
    ApiResult login(@Valid @RequestBody LoginParam loginParam) {
        return adminLoginService.adminLogin(loginParam);
    }

    @PostMapping("/logout")
    @Operation(summary = "管理员退出登录", description = "管理员退出登录")
    ApiResult<?> logout() {
        StpUtil.logout();
        return ApiResult.ok("退出登录成功");
    }

    @PostMapping("/permissions")
    @Operation(summary = "管理员的菜单权限", description = "管理员的菜单权限")
    ApiResult<?> permissions() {
        return adminLoginService.Menu(1);
    }
}
