package com.vtube.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Role;
import com.vtube.domain.RoleMenuPermissions;
import com.vtube.mapper.RoleMenuPermissionsMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.RoleService;
import com.vtube.mapper.RoleMapper;
import com.vtube.vo.Param.assignParams;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @description 针对表【vt_sys_role(管理员角色表)】的数据库操作Service实现
 * @createDate 2024-05-29 13:30:17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

}




