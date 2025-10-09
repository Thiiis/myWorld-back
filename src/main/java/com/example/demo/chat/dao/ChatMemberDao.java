package com.example.demo.chat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.chat.dto.ChatMember;

@Mapper
public interface ChatMemberDao {
    void insert(ChatMember member);
    List<Long> selectMemberIdsByRoom(Long roomId);
}