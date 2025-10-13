package com.example.demo.chat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.chat.dto.ChatMember;

@Mapper
public interface ChatMemberDao {
    void insert(ChatMember member);
    List<Long> selectMemberIdsByRoom(Long roomId);
    int updateLastReadAt(@Param("roomId") Long roomId, @Param("memberId") Long memberId);
}