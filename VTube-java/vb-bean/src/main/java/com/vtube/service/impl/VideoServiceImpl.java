package com.vtube.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Video;
import com.vtube.domain.VideoCategory;
import com.vtube.domain.VideoData;
import com.vtube.domain.VideoEpisode;
import com.vtube.dto.UpLoadVideoDTO;
import com.vtube.dto.UserLoginDTO;
import com.vtube.mapper.VideoEpisodeMapper;
import com.vtube.model.ApiResult;
import com.vtube.service.CategoryService;
import com.vtube.service.VideoCategoryService;
import com.vtube.service.VideoDataService;
import com.vtube.service.VideoService;
import com.vtube.mapper.VideoMapper;
import com.vtube.vo.Param.VideoUpdateParams;
import com.vtube.vo.PlayDetailsVO;
import com.vtube.vo.VideoVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @description 针对表【vt_video(视频表)】的数据库操作Service实现
 * @createDate 2024-05-23 13:59:35
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
        implements VideoService {
    @Resource
    private VideoMapper videoMapper;

    @Resource
    private VideoEpisodeMapper episodeMapper;

    @Resource
    private VideoCategoryService categoryService;

    @Resource
    private VideoDataService videoDataService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addVideoData(VideoVO videoVO) {
        // 插入视频数据
        Video video = new Video();
        video.setTitle(videoVO.getTitle());
        video.setDescription(videoVO.getDescription());
        video.setCreateTime(videoVO.getCreateTime());
        video.setTags(videoVO.getTags());
        video.setType(videoVO.getType());
        video.setImagePath(videoVO.getImagePath());
        video.setUploader("OFFICIAL");
        video.setReleaseTime(videoVO.getReleaseTime());
        videoMapper.insert(video);

        // 插入集数信息
        AtomicInteger index = new AtomicInteger(1);
        videoVO.getEpisodeList().stream().forEach(item -> {
            VideoEpisode episode = new VideoEpisode();
            episode.setVideoPath(item.getVideoPath());
            episode.setTitle(item.getTitle());
            episode.setEpisodeNumber(index.getAndIncrement());
            episode.setVideoId(video.getVideoId());
            episodeMapper.insert(episode);
        });

        // 插入视频信息数据
        videoVO.setVideoId(video.getVideoId());
        videoMapper.addVideoData(videoVO);
        videoMapper.addVideoCategory(videoVO);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateVideoData(VideoVO videoVO) {
        // 更新视频数据
        Video video = new Video();
        video.setVideoId(videoVO.getVideoId());
        video.setTitle(videoVO.getTitle());
        video.setDescription(videoVO.getDescription());
        video.setCreateTime(videoVO.getCreateTime());
        video.setImagePath(videoVO.getImagePath());
        video.setTags(videoVO.getTags());
        video.setType(videoVO.getType());
        video.setUploader("OFFICIAL");
        video.setReleaseTime(videoVO.getReleaseTime());
        videoMapper.updateById(video);
        videoMapper.updateVideoData(videoVO);
        videoMapper.updateVideoCategory(videoVO);

        // 先删除该视频的所有集数信息
        QueryWrapper<VideoEpisode> wrapper = new QueryWrapper<>();
        wrapper.eq("video_id", videoVO.getVideoId());
        episodeMapper.delete(wrapper);
        // 更新集数信息
        AtomicInteger index = new AtomicInteger(1);
        videoVO.getEpisodeList().forEach(item -> {
            VideoEpisode episode = new VideoEpisode();
            episode.setVideoId(videoVO.getVideoId());
            episode.setEpisodeNumber(index.getAndIncrement());
            episode.setTitle(item.getTitle());
            episode.setVideoPath(item.getVideoPath());
            episodeMapper.insert(episode);
        });
        return 0;
    }

    @Override
    public PlayDetailsVO Details(Integer videoId) {
        return videoMapper.getVideoDetails(videoId);
    }

    @Override
    public List<VideoEpisode> Episode(Integer videoId) {
        QueryWrapper<VideoEpisode> wrapper = new QueryWrapper<>();
        wrapper.eq("video_id", videoId);
        wrapper.orderByAsc("episode_number");
        List<VideoEpisode> videoEpisodes = episodeMapper.selectList(wrapper);
        return videoEpisodes;
    }

    @Override
    public ApiResult queryByVideoId(Integer videoId) {
        UserLoginDTO user = (UserLoginDTO) StpUtil.getSession().get("user");
//       // 获取稿件的基础信息
        UpLoadVideoDTO upLoadVideoDTO = videoMapper.selectVideosByUserAndVideoId(user.getUserId(), videoId);
        // 获取稿件的视频信息
        QueryWrapper<VideoEpisode> wrapper = new QueryWrapper<>();
        wrapper.eq("video_Id", videoId);
        List<VideoEpisode> episodes = episodeMapper.selectList(wrapper);
        // 封装数据返回给前端
        HashMap<String, Object> map = new HashMap<>();
        map.put("video", upLoadVideoDTO);
        map.put("episodes", episodes);
        return ApiResult.ok(map);
    }

    @Transactional
    public void addVideo(VideoUpdateParams video) {
        UserLoginDTO user = (UserLoginDTO) StpUtil.getSession().get("user");
        // 插入视频记录
        // 初始化视频状态是审核状态
        video.getVideo().setReviewStatus("1");
        videoMapper.insert(video.getVideo());

        // 插入视频分类记录
        VideoCategory videoCategory = new VideoCategory();
        videoCategory.setVideoId(video.getVideo().getVideoId());
        videoCategory.setCategoryId(video.getCategoryId());
        categoryService.save(videoCategory);
        // 插入视频数据记录
        VideoData videoData = new VideoData();
        videoData.setVideoId(video.getVideo().getVideoId());
        videoData.setUserId(user.getUserId());
        videoDataService.save(videoData);

        // 插入选集数据
        for (VideoEpisode episode : video.getEpisodeList()) {
            episode.setVideoId(video.getVideo().getVideoId()); // 设置视频ID
            episodeMapper.insert(episode);
        }
    }

    @Transactional
    public void updateVideo(VideoUpdateParams video) {
        // 更新视频记录
        videoMapper.updateById(video.getVideo());

        // 更新视频分类记录
        VideoCategory videoCategory = new VideoCategory();
        videoCategory.setVideoId(video.getVideo().getVideoId());
        videoCategory.setCategoryId(video.getCategoryId());
        categoryService.updateById(videoCategory);

        // 删除旧的选集数据
        episodeMapper.delete(new QueryWrapper<VideoEpisode>().eq("video_id", video.getVideo().getVideoId()));

        // 插入新的选集数据
        for (VideoEpisode episode : video.getEpisodeList()) {
            episode.setVideoId(video.getVideo().getVideoId()); // 设置视频ID
            episodeMapper.insert(episode);
        }
    }
}




