package com.vtube.mapper;

import com.vtube.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vtube.vo.Param.LoginParam;
import com.vtube.vo.AdminLoginVO;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【vt_user(用户表)】的数据库操作Mapper
* @createDate 2024-05-22 13:21:56
* @Entity com.vtube.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    //管理员登录
    AdminLoginVO adminLogin(LoginParam loginParam);

}




