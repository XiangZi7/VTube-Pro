package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Follow;
import com.vtube.service.FollowService;
import com.vtube.mapper.FollowMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_follow(关注表)】的数据库操作Service实现
* @createDate 2024-05-07 14:48:48
*/
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow>
    implements FollowService{

}




