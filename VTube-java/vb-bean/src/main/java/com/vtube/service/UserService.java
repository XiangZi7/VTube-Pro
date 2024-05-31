package com.vtube.service;

import com.vtube.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vtube.model.ApiResult;
import com.vtube.vo.Param.LoginParam;
import com.vtube.vo.Param.PassWordParam;

/**
* @author Administrator
* @description 针对表【vt_user(用户表)】的数据库操作Service
* @createDate 2024-05-29 13:48:55
*/
public interface UserService extends IService<User> {

 ApiResult Login(LoginParam loginParam);

 ApiResult SignIn(User user);

 ApiResult ChangePwd(PassWordParam passWordParam);

 // 重置用户密码为000000
 ApiResult resetPassword(Integer userId);
}
