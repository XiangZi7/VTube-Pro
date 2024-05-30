package com.vtube.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Role;
import com.vtube.domain.User;
import com.vtube.mapper.UserMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    @GetMapping("/list")
    ApiResult list(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                   @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, User user) {
        Page<User> page = new Page<>(pageNum, pageSize);

        return ApiResult.ok(userMapper.userList(page, user));
//        return ApiResult.ok(userService.list());
    }
}
