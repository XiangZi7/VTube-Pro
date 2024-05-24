package com.vtube.service;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.vtube.domain.Role;
import com.vtube.domain.UserRole;
import com.vtube.mapper.RoleMapper;
import com.vtube.vo.AdminLoginVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展

public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleService roleService;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        list.add("101");
        list.add("user.add");
        list.add("user.update");
        list.add("user.get");
        list.add("art.*");
        return list;
    }

    @Override
    public List<String> getRoleList(Object loginId, String s) {
        return roleMapper.ByUserIdFindRoleName(Integer.parseInt(loginId.toString()));
    }
}
