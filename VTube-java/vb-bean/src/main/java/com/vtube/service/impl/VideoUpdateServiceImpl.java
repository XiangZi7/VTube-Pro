package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.VideoUpdate;
import com.vtube.service.VideoUpdateService;
import com.vtube.mapper.VideoUpdateMapper;
import com.vtube.vo.WeeklyAnimeUpdateVO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【vt_video_update(番剧更新表)】的数据库操作Service实现
* @createDate 2024-05-22 16:43:58
*/
@Service
public class VideoUpdateServiceImpl extends ServiceImpl<VideoUpdateMapper, VideoUpdate>
    implements VideoUpdateService{
    @Resource
    private VideoUpdateMapper videoUpdateMapper;
    @Override
    public Map<String, List<WeeklyAnimeUpdateVO>> getWeeklyAnimeUpdates() {
        List<WeeklyAnimeUpdateVO> weeklyAnimeUpdates = videoUpdateMapper.getWeeklyAnimeUpdates();
        Map<String, List<WeeklyAnimeUpdateVO>> groupedUpdates = new HashMap<>();

        // 初始化七个子数组分别对应周一到周日
        for (int i = 1; i <= 7; i++) {
            groupedUpdates.put(String.valueOf(i), new ArrayList<>());
        }

        // 按week_day分组
        for (WeeklyAnimeUpdateVO update : weeklyAnimeUpdates) {
            groupedUpdates.computeIfAbsent(String.valueOf(update.getWeekDay()), k -> new ArrayList<>()).add(update);
        }
        return groupedUpdates;
    }
}




