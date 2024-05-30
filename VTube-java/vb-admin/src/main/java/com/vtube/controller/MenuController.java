package com.vtube.controller;

import com.vtube.domain.Menu;
import com.vtube.mapper.MenuMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;


    @GetMapping("/list")
    ApiResult list() {
        return ApiResult.ok(menuService.list());
    }

    @GetMapping("/allMenuData")
    ApiResult allMenuData() {
        return ApiResult.ok(menuService.list());
    }

    @PostMapping("/addMenu")
    ApiResult addMenu(@RequestBody Menu menu) {
        menuService.save(menu);
        return ApiResult.ok("新增成功");
    }

    @PostMapping("/editMenu")
    ApiResult editMenu(@RequestBody Menu menu) {
        menuService.updateById(menu);
        return ApiResult.ok("修改成功");
    }
    @PostMapping("/deleteMenu")
    @Operation(summary = "菜单删除", description = "菜单删除")
    ApiResult deletes(@RequestBody List<Integer> ids) {
        menuService.removeBatchByIds(ids);
        return ApiResult.ok("删除成功");
    }
}
