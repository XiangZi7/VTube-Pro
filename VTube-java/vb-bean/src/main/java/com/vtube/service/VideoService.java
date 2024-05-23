package com.vtube.service;

import com.vtube.domain.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vtube.domain.VideoEpisode;
import com.vtube.vo.PlayDetailsVO;
import com.vtube.vo.VideoVO;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vt_video(视频表)】的数据库操作Service
* @createDate 2024-05-23 13:59:35
*/
public interface VideoService extends IService<Video> {
    int addVideoData(VideoVO videoVO);

    int updateVideoData(VideoVO videoVO);

    // 视频详情
    PlayDetailsVO Details(Integer videoId);

    // 视频选集
    List<VideoEpisode> Episode(Integer videoId);
}
