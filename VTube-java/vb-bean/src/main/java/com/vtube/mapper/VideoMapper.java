package com.vtube.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vtube.domain.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【vt_video(视频表)】的数据库操作Mapper
* @createDate 2024-04-29 17:02:48
* @Entity com.vtube.domain.Video
*/
public interface VideoMapper extends BaseMapper<Video> {

    //    用户列表
    IPage<Video> VideoList(IPage<Video> page);
}




