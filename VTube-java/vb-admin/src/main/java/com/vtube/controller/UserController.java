package com.vtube.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Role;
import com.vtube.domain.User;
import com.vtube.mapper.UserMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    }

    @PostMapping("/add")
    ApiResult add(@RequestBody User user) {
        userService.save(user);
        return ApiResult.ok("新增成功");
    }

    @PostMapping("/edit")
    ApiResult edit(@RequestBody User user) {
        userService.updateById(user);
        return ApiResult.ok("修改成功");
    }

    @PostMapping("/deletes")
    ApiResult deletes(@RequestBody List<Integer> ids) {
        userService.removeBatchByIds(ids);
        return ApiResult.ok("删除成功");
    }

    @PostMapping("/reset")
    ApiResult reset(@RequestBody JSONObject jsonObject) {
        String userId = jsonObject.getStr("userId");
        userService.resetPassword(Integer.valueOf(userId));
        return ApiResult.ok("重置成功");
    }
}
