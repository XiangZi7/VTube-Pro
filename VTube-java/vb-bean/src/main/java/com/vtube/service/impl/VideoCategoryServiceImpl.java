package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.VideoCategory;
import com.vtube.service.VideoCategoryService;
import com.vtube.mapper.VideoCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_video_category(视频分类关联表)】的数据库操作Service实现
* @createDate 2024-05-16 10:15:32
*/
@Service
public class VideoCategoryServiceImpl extends ServiceImpl<VideoCategoryMapper, VideoCategory>
    implements VideoCategoryService{

}




