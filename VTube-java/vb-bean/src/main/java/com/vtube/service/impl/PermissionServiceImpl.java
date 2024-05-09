package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Permission;
import com.vtube.service.PermissionService;
import com.vtube.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_permission(权限表)】的数据库操作Service实现
* @createDate 2024-05-07 14:48:48
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




