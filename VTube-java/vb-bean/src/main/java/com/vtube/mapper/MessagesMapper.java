package com.vtube.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vtube.domain.Messages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vtube.dto.ChatSessionDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【vt_messages(消息表)】的数据库操作Mapper
 * @createDate 2024-06-06 16:41:27
 * @Entity com.vtube.domain.Messages
 */
public interface MessagesMapper extends BaseMapper<Messages> {
    @Select("SELECT * FROM vt_messages WHERE chat_id = #{chatId} ORDER BY timestamp DESC")
    IPage<Messages> selectMessagesByChatId(Page<?> page, @Param("chatId") String chatId);

    IPage<ChatSessionDTO> getChatSessionsByUserId(Page page, @Param("userId") Long userId);

}




