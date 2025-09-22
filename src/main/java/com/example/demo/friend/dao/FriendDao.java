package com.example.demo.friend.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.friend.dto.Friend;

@Mapper
public interface FriendDao {
    // 친구 요청
    void insert(Friend friend);

    
}
