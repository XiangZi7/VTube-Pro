package com.vtube.service;

import com.vtube.domain.VideoUpdate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vtube.vo.WeeklyAnimeUpdateVO;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【vt_video_update(番剧更新表)】的数据库操作Service
* @createDate 2024-05-22 16:43:58
*/
public interface VideoUpdateService extends IService<VideoUpdate> {

    // 获取周番更新表
    Map<String, List<WeeklyAnimeUpdateVO>> getWeeklyAnimeUpdates();

}
