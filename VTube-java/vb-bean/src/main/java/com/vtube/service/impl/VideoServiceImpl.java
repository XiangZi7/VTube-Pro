package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Video;
import com.vtube.service.VideoService;
import com.vtube.mapper.VideoMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_video(视频表)】的数据库操作Service实现
* @createDate 2024-05-07 14:48:49
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

}




