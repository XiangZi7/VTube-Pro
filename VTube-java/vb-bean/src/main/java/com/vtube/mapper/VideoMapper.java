package com.vtube.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vtube.domain.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vtube.domain.VideoEpisode;
import com.vtube.dto.ProjectorDTO;
import com.vtube.dto.UpLoadVideoDTO;
import com.vtube.dto.VideoDTO;
import com.vtube.dto.VideoSummaryDTO;
import com.vtube.vo.Param.VideoParam;
import com.vtube.vo.PlayDetailsVO;
import com.vtube.vo.VideoVO;
import com.vtube.vo.WeeklyAnimeUpdateVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【vt_video(视频表)】的数据库操作Mapper
 * @createDate 2024-05-23 13:59:35
 * @Entity com.vtube.domain.Video
 */
public interface VideoMapper extends BaseMapper<Video> {
    // 视频列表
    IPage<VideoVO> VideoList(IPage<Video> page, @Param("video") VideoParam video);

    // 视频审核
    IPage<VideoVO> VideoAuditingList(IPage<Video> page, @Param("video") VideoParam video);

    int addVideoData(VideoVO videoVO);

    int updateVideoData(VideoVO videoVO);

    int addVideoCategory(VideoVO videoVO);

    int updateVideoCategory(VideoVO videoVO);

    // 获取视频详情
    PlayDetailsVO getVideoDetails(Integer videoId);

    // 获取周番更新表
    IPage<WeeklyAnimeUpdateVO> weekList(@Param("video") WeeklyAnimeUpdateVO video, IPage<Video> page);

    // 查出周番更新表没有的动漫
    IPage<VideoVO> VideoByWeekIsNullList(IPage<Video> page, @Param("video") VideoParam video);

    // PC端的TOP10
    List<VideoDTO> PCTOP10();

    // PC端 推荐视频 以播放量高、点赞多、最新发布的视频为标准
    List<VideoDTO> findRecommendedVideos();

    // PC番组计划
    IPage<ProjectorDTO> ProjectorList(IPage<Video> page, @Param("video") ProjectorDTO video);

    // 根据用户ID查询视频信息
    IPage<VideoSummaryDTO> VideoManagementList(IPage<VideoSummaryDTO> page, @Param("userId") Integer userId, @Param("title") String title);

    // 根据用户ID和视频ID查询视频信息
    UpLoadVideoDTO selectVideosByUserAndVideoId(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    // 首页的最新8个视频
    @Select("SELECT v.*,vd.views,vd.likes,vu.nick_name,vu.avatar_path FROM vt_video v JOIN vt_video_data vd ON v.video_id = vd.video_id join vt_user vu on vu.user_id = vd.user_id ORDER BY v.create_time DESC LIMIT 8")
    List<VideoDTO> selectLatestVideos8();

    // 播放量最高的视频
    @Select("SELECT v.*,vd.views,vd.likes,vu.nick_name,vu.avatar_path FROM vt_video v JOIN vt_video_data vd ON v.video_id = vd.video_id join vt_user vu on vu.user_id = vd.user_id ORDER BY vd.views DESC")
    IPage<VideoDTO> selectTopPlayedVideos(IPage<VideoDTO> page);

}




