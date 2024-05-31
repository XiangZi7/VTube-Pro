package com.vtube.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vtube.domain.Comments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vtube.domain.Video;
import com.vtube.dto.CommentDTO;
import com.vtube.model.ApiResult;

/**
* @author Administrator
* @description 针对表【vt_comments(评论表)】的数据库操作Service
* @createDate 2024-05-31 16:39:09
*/
public interface CommentsService extends IService<Comments> {

    IPage getPagedCommentsWithChildren(IPage<CommentDTO> page,Integer targetType,Integer videoId);

}
