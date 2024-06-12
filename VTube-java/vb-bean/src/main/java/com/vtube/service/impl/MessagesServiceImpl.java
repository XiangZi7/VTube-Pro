package com.vtube.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vtube.domain.Messages;
import com.vtube.service.MessagesService;
import com.vtube.mapper.MessagesMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author Administrator
* @description 针对表【vt_messages(消息表)】的数据库操作Service实现
* @createDate 2024-06-06 16:41:27
*/
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages>
    implements MessagesService{

    @Resource
    private MessagesMapper messageMapper;
    @Override
    public IPage<Messages> getMessagesByChatId(String chatId, int pageNum, int pageSize) {
        Page<Messages> page = new Page<>(pageNum, pageSize);
        return messageMapper.selectMessagesByChatId(page, chatId);
    }

    @Override
    public List<Messages> getMessagesByChatId(String chatId) {
        return this.lambdaQuery().eq(Messages::getChatId, chatId).list();
    }
}




