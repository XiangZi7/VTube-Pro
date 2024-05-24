package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.RoleMenuPermissions;
import com.vtube.service.RoleMenuPermissionsService;
import com.vtube.mapper.RoleMenuPermissionsMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_role_menu_permissions(角色与菜单权限关联表)】的数据库操作Service实现
* @createDate 2024-05-24 09:53:02
*/
@Service
public class RoleMenuPermissionsServiceImpl extends ServiceImpl<RoleMenuPermissionsMapper, RoleMenuPermissions>
    implements RoleMenuPermissionsService{

}



