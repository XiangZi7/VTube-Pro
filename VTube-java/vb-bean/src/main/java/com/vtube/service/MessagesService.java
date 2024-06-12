package com.vtube.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vtube.domain.Messages;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vt_messages(消息表)】的数据库操作Service
* @createDate 2024-06-06 16:41:27
*/
public interface MessagesService extends IService<Messages> {
    IPage<Messages> getMessagesByChatId(String chatId, int pageNum, int pageSize);

    List<Messages> getMessagesByChatId(String chatId);

}
