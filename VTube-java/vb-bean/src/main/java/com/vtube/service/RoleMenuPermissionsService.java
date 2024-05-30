package com.vtube.service;

import com.vtube.domain.RoleMenuPermissions;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vtube.model.ApiResult;
import com.vtube.vo.Param.assignParams;

/**
* @author Administrator
* @description 针对表【vt_sys_role_menu_permissions(管理员角色与菜单权限关联表)】的数据库操作Service
* @createDate 2024-05-29 13:31:25
*/
public interface RoleMenuPermissionsService extends IService<RoleMenuPermissions> {
    ApiResult assign(assignParams params);
}
