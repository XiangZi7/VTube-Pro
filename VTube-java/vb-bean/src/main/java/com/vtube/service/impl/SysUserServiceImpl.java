package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.SysUser;
import com.vtube.service.SysUserService;
import com.vtube.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_sys_user(管理员表)】的数据库操作Service实现
* @createDate 2024-05-29 13:39:07
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




