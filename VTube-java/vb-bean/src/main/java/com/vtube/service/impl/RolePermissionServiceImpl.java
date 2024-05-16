package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.RolePermission;
import com.vtube.service.RolePermissionService;
import com.vtube.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_role_permission(角色权限关联表)】的数据库操作Service实现
* @createDate 2024-05-16 10:15:32
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




