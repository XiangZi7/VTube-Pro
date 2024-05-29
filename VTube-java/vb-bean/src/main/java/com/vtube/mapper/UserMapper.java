package com.vtube.mapper;

import com.vtube.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vtube.dto.UserLoginDTO;
import com.vtube.vo.Param.LoginParam;

/**
* @author Administrator
* @description 针对表【vt_user(用户表)】的数据库操作Mapper
* @createDate 2024-05-29 13:48:55
* @Entity com.vtube.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    UserLoginDTO userLogin(LoginParam param);
}




