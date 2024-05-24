package com.vtube.service;

import cn.dev33.satoken.stp.StpUtil;
import com.vtube.domain.Menu;
import com.vtube.mapper.UserMapper;
import com.vtube.model.ApiResult;
import com.vtube.vo.AdminLoginVO;
import com.vtube.vo.Param.LoginParam;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLoginService {
    @Autowired
    UserService userService;
    @Resource
    UserMapper userMapper;


    public ApiResult adminLogin(LoginParam loginParam) {
        if (loginParam == null) return ApiResult.error("账号密码不能为空");

        AdminLoginVO adminLoginVO = userMapper.adminLogin(loginParam);
        if (adminLoginVO == null) return ApiResult.error("账号或密码错误");

        StpUtil.login(adminLoginVO.getUserId());
        adminLoginVO.setToken(StpUtil.getTokenValue());

        return ApiResult.ok(adminLoginVO);
    }

    public ApiResult Menu(Integer UserId) {
        List<Menu> menus = userMapper.ByUserIdFindMenu(UserId);
        if (menus == null) return ApiResult.error("该用户未分配菜单");
        return ApiResult.ok(menus);
    }
}
