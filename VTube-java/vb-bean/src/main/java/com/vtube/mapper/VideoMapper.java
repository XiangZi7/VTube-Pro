package com.vtube.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vtube.domain.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vtube.vo.Param.VideoVOParam;
import com.vtube.vo.VideoVO;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【vt_video(视频表)】的数据库操作Mapper
* @createDate 2024-05-16 10:15:32
* @Entity com.vtube.domain.Video
*/
public interface VideoMapper extends BaseMapper<Video> {
    IPage<VideoVO> VideoList(IPage<Video> page, @Param("video") VideoVOParam video);

}




