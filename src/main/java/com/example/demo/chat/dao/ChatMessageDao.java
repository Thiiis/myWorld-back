package com.example.demo.chat.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.chat.dto.ChatMessage;
import com.example.demo.chat.dto.ChatMessageResponse;

@Mapper
public interface ChatMessageDao {
    void insert(ChatMessage message);
    List<ChatMessageResponse> selectByRoomId(Long roomId);
    ChatMessageResponse selectById(Long id);
    int countUnreadMessages(@Param("roomId") Long roomId, @Param("memberId") Long memberId);
}