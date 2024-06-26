package com.vtube.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.User;
import com.vtube.dto.UserLoginDTO;
import com.vtube.model.ApiResult;
import com.vtube.service.UserService;
import com.vtube.mapper.UserMapper;
import com.vtube.vo.Param.LoginParam;
import com.vtube.vo.Param.PassWordParam;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author Administrator
 * @description 针对表【vt_user(用户表)】的数据库操作Service实现
 * @createDate 2024-05-29 13:48:55
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ApiResult Login(LoginParam loginParam) {
        if (loginParam == null) return ApiResult.error("账号密码不能为空");

        UserLoginDTO userLoginDTO = userMapper.userLogin(loginParam);
        if (userLoginDTO == null) return ApiResult.error("账号或密码错误");

        StpUtil.login(userLoginDTO.getUserId());
        userLoginDTO.setToken(StpUtil.getTokenValue());
        // 存入当前用户的数据
        StpUtil.getSession().set("user", userLoginDTO);

        return ApiResult.ok(userLoginDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult SignIn(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", user.getPhone());

        User one = userMapper.selectOne(wrapper);
        if (one == null) {
            user.setCreateTime(LocalDateTime.now());
            userMapper.insert(user);
            return ApiResult.ok("注册成功");
        }
        return ApiResult.error("电话号码也被注册");
    }

    @Override
    public ApiResult ChangePwd(PassWordParam PassWord) {
        UserLoginDTO user = (UserLoginDTO) StpUtil.getSession().get("user");

        // 构建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getUserId());
        // 添加旧密码校验条件
        queryWrapper.eq("password", PassWord.getOldPassWord());

        // 查询是否有匹配的用户
        User existingUser = userMapper.selectOne(queryWrapper);
        if (existingUser == null) {
            return ApiResult.error("旧密码错误");
        }

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", user.getUserId());
        // 只需要更新密码字段
        User updatedUser = new User();
        updatedUser.setPassword(PassWord.getNewPassWord());

        // 更新用户密码
        userMapper.update(updatedUser, updateWrapper);
        return ApiResult.ok("修改密码成功");
    }

    @Override
    public ApiResult resetPassword(Integer userId) {
        User user = this.getById(userId);
        if (user == null) {
            return ApiResult.error("用户不存在"); // 用户不存在
        }
        user.setPassword("000000");
        return ApiResult.ok(this.updateById(user));
    }
}




