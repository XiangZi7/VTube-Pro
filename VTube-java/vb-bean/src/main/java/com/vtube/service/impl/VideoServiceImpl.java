package com.vtube.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Video;
import com.vtube.domain.VideoEpisode;
import com.vtube.mapper.VideoEpisodeMapper;
import com.vtube.service.VideoService;
import com.vtube.mapper.VideoMapper;
import com.vtube.vo.VideoVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @description 针对表【vt_video(视频表)】的数据库操作Service实现
 * @createDate 2024-05-20 11:10:50
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
        implements VideoService {
    @Resource
    private VideoMapper videoMapper;

    @Resource
    private VideoEpisodeMapper episodeMapper;

    @Override
    public int addVideoData(VideoVO videoVO) {
        // 插入视频数据
        Video video = new Video();
        video.setTitle(videoVO.getTitle());
        video.setDescription(videoVO.getDescription());
        video.setCreateTime(videoVO.getCreateTime());
        videoMapper.insert(video);

        // 插入集数信息
        AtomicInteger index = new AtomicInteger(1);
        videoVO.getEpisodeList().stream().forEach(item -> {
            VideoEpisode episode = new VideoEpisode();
            episode.setVideoPath(item.getVideoPath());
            episode.setTitle(item.getTitle());
            episode.setEpisodeNumber(index.getAndIncrement());
            episode.setVideoId(videoVO.getVideoId());
            episodeMapper.insert(episode);
        });

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
        video.setImagePath(videoVO.getImagePath());
        videoMapper.updateById(video);
        videoMapper.updateVideoData(videoVO);
        videoMapper.updateVideoCategory(videoVO);

        // 更新集数信息
        AtomicInteger index = new AtomicInteger(1);
        videoVO.getEpisodeList().forEach(item -> {
            VideoEpisode episode = new VideoEpisode();
            episode.setVideoId(videoVO.getVideoId());
            episode.setEpisodeNumber(index.getAndIncrement());
            episode.setTitle(item.getTitle());
            episode.setVideoPath(item.getVideoPath());
            if (item.getEpisodeId() == null) {
                episodeMapper.insert(episode);
            } else {
                episode.setEpisodeId(item.getEpisodeId());
                episodeMapper.updateById(episode);
            }
        });
        return 0;
    }
}




