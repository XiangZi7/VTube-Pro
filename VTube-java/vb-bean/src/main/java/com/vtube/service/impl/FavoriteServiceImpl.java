package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Favorite;
import com.vtube.service.FavoriteService;
import com.vtube.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_favorite(收藏表)】的数据库操作Service实现
* @createDate 2024-05-16 10:15:32
*/
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite>
    implements FavoriteService{

}




