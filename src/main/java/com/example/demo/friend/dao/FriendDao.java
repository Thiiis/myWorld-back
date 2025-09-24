package com.example.demo.friend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.friend.dto.Friend;
import com.example.demo.friend.dto.FriendCreateResponse;

@Mapper
public interface FriendDao {
    // 친구 요청
    void insert(Friend friend);
    // 친구(관계) 조회
    Friend selectById(Long id);
    // 상태 변경
    void updateStatus(Friend friend);
    //친구 요청 조회
    List<Friend> selectByAccepterId(Long accId);
}
