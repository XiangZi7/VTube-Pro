package com.vtube.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vtube.domain.Video;
import com.vtube.dto.CommentDTO;
import com.vtube.vo.WeeklyAnimeUpdateVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【vt_comments(评论表)】的数据库操作Mapper
 * @createDate 2024-05-31 16:39:09
 * @Entity com.vtube.domain.Comments
 */
public interface CommentsMapper extends BaseMapper<Comments> {

    IPage<CommentDTO> getTopLevelComments(IPage<CommentDTO> page, @Param("targetType") Integer targetType, @Param("videoId") Integer videoId);

    List<CommentDTO> getChildComments(@Param("parentIds") List<Integer> parentIds);
}




