package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Admin;
import com.vtube.service.AdminService;
import com.vtube.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_admin(管理员表)】的数据库操作Service实现
* @createDate 2024-04-29 17:02:48
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




