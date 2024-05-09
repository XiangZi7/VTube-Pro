package com.vtube.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vtube.domain.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【vt_video(视频表)】的数据库操作Mapper
* @createDate 2024-05-07 14:48:49
* @Entity com.vtube.domain.Video
*/
public interface VideoMapper extends BaseMapper<Video> {
    IPage<Video> VideoList(IPage<Video> page, @Param("video") Video video);

}




