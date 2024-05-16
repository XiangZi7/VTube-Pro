package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.VideoData;
import com.vtube.service.VideoDataService;
import com.vtube.mapper.VideoDataMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_video_data(视频数据表)】的数据库操作Service实现
* @createDate 2024-05-16 10:37:04
*/
@Service
public class VideoDataServiceImpl extends ServiceImpl<VideoDataMapper, VideoData>
    implements VideoDataService{

}




