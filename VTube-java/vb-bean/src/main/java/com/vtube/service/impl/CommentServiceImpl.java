package com.vtube.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Comment;
import com.vtube.service.CommentService;
import com.vtube.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【vt_comment(评论表)】的数据库操作Service实现
* @createDate 2024-04-29 17:02:48
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




