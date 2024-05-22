package com.vtube.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.vtube.mapper.UserMapper;
import com.vtube.model.ApiResult;
import com.vtube.vo.Param.LoginParam;
import com.vtube.vo.AdminLoginVO;
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
    private UserMapper userMapper;

    @PostMapping("/login")
    @Operation(summary = "管理员登录", description = "管理员登录")
    public ApiResult<?> login(@Valid @RequestBody LoginParam loginParam) {
        // 校验参数是否为空
        if (loginParam == null) {
            return ApiResult.error("请求参数不能为空");
        }

        AdminLoginVO adminLoginVO = userMapper.adminLogin(loginParam);

        if (adminLoginVO == null) return ApiResult.error("账号或者密码错误");
        StpUtil.login(adminLoginVO.getUserId());
        adminLoginVO.setToken(StpUtil.getTokenValue());

        return ApiResult.ok(adminLoginVO);
    }

    @PostMapping("/logout")
    @Operation(summary = "管理员退出登录", description = "管理员退出登录")
    public ApiResult<?> logout() {
        StpUtil.logout();
        return ApiResult.ok("退出登录成功");
    }
}
