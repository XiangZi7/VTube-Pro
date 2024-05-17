package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Video;
import com.vtube.service.VideoService;
import com.vtube.mapper.VideoMapper;
import com.vtube.vo.VideoVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_video(视频表)】的数据库操作Service实现
* @createDate 2024-05-16 10:15:32
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{
    @Resource
    private VideoMapper videoMapper;

    @Override
    public int addVideoData(VideoVO videoVO) {
        // 插入视频数据
        Video video = new Video();
        video.setTitle(videoVO.getTitle());
        video.setDescription(videoVO.getDescription());
        video.setCreateTime(videoVO.getCreateTime());
        videoMapper.insert(video);

        // 插入视频信息数据
        videoVO.setVideoId(video.getVideoId());
        videoMapper.addVideoData(videoVO);
        videoMapper.addVideoCategory(videoVO);
        return 0;
    }

    @Override
    public int updateVideoData(VideoVO videoVO) {
        // 更新视频数据
        Video video = new Video();
        video.setVideoId(videoVO.getVideoId());
        video.setTitle(videoVO.getTitle());
        video.setDescription(videoVO.getDescription());
        video.setCreateTime(videoVO.getCreateTime());
        video.setVideoPath(videoVO.getVideoPath());
        video.setImagePath(video.getImagePath());
        videoMapper.updateById(video);
        videoMapper.updateVideoData(videoVO);
        videoMapper.updateVideoCategory(videoVO);

        return 0;
    }
}




