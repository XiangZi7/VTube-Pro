package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Like;
import com.vtube.service.LikeService;
import com.vtube.mapper.LikeMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_like(点赞表)】的数据库操作Service实现
* @createDate 2024-05-07 14:48:48
*/
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like>
    implements LikeService{

}




