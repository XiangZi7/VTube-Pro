package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.UserRole;
import com.vtube.service.UserRoleService;
import com.vtube.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_sys_user_role(管理员用户角色关联表)】的数据库操作Service实现
* @createDate 2024-05-29 13:33:33
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




