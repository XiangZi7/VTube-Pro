package com.vtube.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.RoleMenuPermissions;
import com.vtube.model.ApiResult;
import com.vtube.service.RoleMenuPermissionsService;
import com.vtube.mapper.RoleMenuPermissionsMapper;
import com.vtube.vo.Param.assignParams;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @description 针对表【vt_sys_role_menu_permissions(管理员角色与菜单权限关联表)】的数据库操作Service实现
 * @createDate 2024-05-29 13:31:25
 */
@Service
public class RoleMenuPermissionsServiceImpl extends ServiceImpl<RoleMenuPermissionsMapper, RoleMenuPermissions>
        implements RoleMenuPermissionsService {

    @Resource
    private RoleMenuPermissionsMapper roleMenuPermissionsMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult assign(assignParams params) {
        // 使用 LambdaQueryWrapper 简化条件查询代码
        LambdaQueryWrapper<RoleMenuPermissions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenuPermissions::getRoleId, params.getRoleId());

        // 首先删除所有旧的角色菜单权限记录
        roleMenuPermissionsMapper.delete(queryWrapper);

        // 逐条插入
        if (params.getMenuId() != null && !params.getMenuId().isEmpty()) {
            for (Integer menuId : params.getMenuId()) {
                RoleMenuPermissions roleMenuPermissions = new RoleMenuPermissions();
                roleMenuPermissions.setRoleId(params.getRoleId());
                roleMenuPermissions.setMenuId(menuId);
                roleMenuPermissionsMapper.insert(roleMenuPermissions);
            }
        }

        return ApiResult.ok("授权成功");
    }
}




