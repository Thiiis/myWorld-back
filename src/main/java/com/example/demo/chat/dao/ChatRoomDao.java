package com.example.demo.chat.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.chat.dto.ChatRoom;
import com.example.demo.chat.dto.ChatRoomResponse;

@Mapper
public interface ChatRoomDao {
    void insert(ChatRoom room);
    ChatRoom selectById(Long id);
    List<ChatRoomResponse> selectByMid(@Param("mid") Long mid);
    ChatRoom selectByMembers(@Param("loginMid") Long loginMid, @Param("mid") Long mid);
    void touchUpdatedAt(Long roomId);
}
