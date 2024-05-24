package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Menu;
import com.vtube.service.MenuService;
import com.vtube.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_menu(菜单表)】的数据库操作Service实现
* @createDate 2024-05-24 09:53:02
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




