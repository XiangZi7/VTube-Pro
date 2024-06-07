package com.vtube.mapper;

import com.vtube.domain.Chats;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author Administrator
* @description 针对表【vt_chats(聊天会话表)】的数据库操作Mapper
* @createDate 2024-06-06 16:41:27
* @Entity com.vtube.domain.Chats
*/
public interface ChatsMapper extends BaseMapper<Chats> {
    String getChatId(@Param("fromUser") String fromUser, @Param("toUser") String toUser);

}




