package com.vtube.controller;

import com.vtube.domain.User;
import com.vtube.model.ApiResult;
import com.vtube.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "管理员")
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private UserService userService;

    @PostMapping("/edit")
    ApiResult edit(@RequestBody User user) {
        return ApiResult.ok(userService.updateById(user));
    }
}
