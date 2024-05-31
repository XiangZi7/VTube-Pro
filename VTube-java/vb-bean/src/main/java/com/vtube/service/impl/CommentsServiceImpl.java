package com.vtube.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Comments;
import com.vtube.domain.Video;
import com.vtube.dto.CommentDTO;
import com.vtube.model.ApiResult;
import com.vtube.service.CommentsService;
import com.vtube.mapper.CommentsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @description 针对表【vt_comments(评论表)】的数据库操作Service实现
 * @createDate 2024-05-31 16:39:09
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments>
        implements CommentsService {

    @Resource
    private CommentsMapper commentsMapper;

    @Override
    public IPage getPagedCommentsWithChildren(IPage<CommentDTO> page, Integer targetType, Integer videoId) {
        IPage<CommentDTO> topLevelCommentsPage = commentsMapper.getTopLevelComments(page, targetType, videoId);
        List<CommentDTO> topLevelComments = topLevelCommentsPage.getRecords();

        if (topLevelComments.isEmpty()) {
            return topLevelCommentsPage;
        }

        // 获取所有父评论ID
        List<Integer> parentIds = topLevelComments.stream()
                .map(CommentDTO::getCommentId)
                .collect(Collectors.toList());

        // 获取所有子评论
        List<CommentDTO> childComments = commentsMapper.getChildComments(parentIds);

        // 组织数据结构，将子评论加入到父评论的children中
        topLevelComments.forEach(topComment -> {
            List<CommentDTO> children = childComments.stream()
                    .filter(child -> child.getParentId().equals(topComment.getCommentId()))
                    .collect(Collectors.toList());
            topComment.setChildren(children);
        });
        return topLevelCommentsPage;
    }
}




