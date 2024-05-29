package com.vtube.mapper;

import com.vtube.domain.Menu;
import com.vtube.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vtube.vo.AdminLoginVO;
import com.vtube.vo.Param.LoginParam;

import java.util.List;


/**
* @author Administrator
* @description 针对表【vt_sys_user(管理员表)】的数据库操作Mapper
* @createDate 2024-05-29 13:39:07
* @Entity com.vtube.domain.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {
    //管理员登录
    AdminLoginVO adminLogin(LoginParam loginParam);
    // 查找用户的菜单、权限
    List<Menu> ByUserIdFindMenu(Integer UserId);
}




