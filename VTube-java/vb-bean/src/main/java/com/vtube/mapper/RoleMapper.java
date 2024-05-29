package com.vtube.mapper;

import com.vtube.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vt_sys_role(管理员角色表)】的数据库操作Mapper
* @createDate 2024-05-29 13:30:17
* @Entity com.vtube.domain.Role
*/
public interface RoleMapper extends BaseMapper<Role> {
    List<String> ByUserIdFindRoleName(Integer userId);

}




