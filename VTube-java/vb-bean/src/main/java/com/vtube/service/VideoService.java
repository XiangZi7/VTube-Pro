package com.vtube.service;

import com.vtube.domain.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vtube.vo.VideoVO;

/**
* @author Administrator
* @description 针对表【vt_video(视频表)】的数据库操作Service
* @createDate 2024-05-16 10:15:32
*/
public interface VideoService extends IService<Video> {


    int addVideoData(VideoVO videoVO);

    int updateVideoData(VideoVO videoVO);

}
