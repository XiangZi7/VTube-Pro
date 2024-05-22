package com.vtube.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vtube.domain.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vtube.vo.Param.VideoParam;
import com.vtube.vo.VideoVO;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【vt_video(视频表)】的数据库操作Mapper
* @createDate 2024-05-20 11:10:50
* @Entity com.vtube.domain.Video
*/
public interface VideoMapper extends BaseMapper<Video> {
    IPage<VideoVO> VideoList(IPage<Video> page, @Param("video") VideoParam video);

    int addVideoData(VideoVO videoVO);

    int updateVideoData(VideoVO videoVO);
    int addVideoCategory(VideoVO videoVO);
    int updateVideoCategory(VideoVO videoVO);

}




