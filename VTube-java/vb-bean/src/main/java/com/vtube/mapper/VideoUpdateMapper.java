package com.vtube.mapper;

import com.vtube.domain.VideoUpdate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vtube.vo.WeeklyAnimeUpdateVO;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vt_video_update(番剧更新表)】的数据库操作Mapper
* @createDate 2024-05-22 16:43:58
* @Entity com.vtube.domain.VideoUpdate
*/
public interface VideoUpdateMapper extends BaseMapper<VideoUpdate> {

    List<WeeklyAnimeUpdateVO> getWeeklyAnimeUpdates ();
}




