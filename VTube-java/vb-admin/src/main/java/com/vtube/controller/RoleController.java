package com.vtube.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Menu;
import com.vtube.domain.Role;
import com.vtube.domain.RoleMenuPermissions;
import com.vtube.model.ApiResult;
import com.vtube.service.RoleMenuPermissionsService;
import com.vtube.service.RoleService;
import com.vtube.vo.Param.VideoParam;
import com.vtube.vo.Param.assignParams;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuPermissionsService roleMenuPermissionsService;


    @GetMapping("/list")
    ApiResult list(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                   @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, String name) {
        Page<Role> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (name != null) {
            wrapper.like("name", name);
        }
        Page<Role> page1 = roleService.page(page, wrapper);
        return ApiResult.ok(page1);
    }

    @PostMapping("/add")
    ApiResult add(@RequestBody Role role) {
        roleService.save(role);
        return ApiResult.ok("新增成功");
    }

    @PostMapping("/edit")
    ApiResult edit(@RequestBody Role role) {
        roleService.updateById(role);
        return ApiResult.ok("修改成功");
    }

    @PostMapping("/deletes")
    @Operation(summary = "角色删除", description = "角色删除")
    ApiResult deletes(@RequestBody List<Integer> ids) {
        roleService.removeBatchByIds(ids);
        return ApiResult.ok("删除成功");
    }

    @PostMapping("/menu")
    @Operation(summary = "根据角色Id查找菜单", description = "根据角色Id查找菜单")
    ApiResult menu(@RequestBody JSONObject jsonObject) {
        String roleId = jsonObject.getStr("roleId");
        QueryWrapper<RoleMenuPermissions> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        List<RoleMenuPermissions> list = roleMenuPermissionsService.list(wrapper);
        return ApiResult.ok(list);
    }

    @PostMapping("/assign")
    ApiResult assign(@RequestBody assignParams params) {

        return roleMenuPermissionsService.assign(params);
    }
}
