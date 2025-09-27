package com.example.demo.friend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.friend.dto.Friend;
import com.example.demo.friend.dto.FriendRequestListResponse;
import com.example.demo.friend.dto.FriendListResponse;

@Mapper
public interface FriendDao {
    // 친구 요청
    void insert(Friend friend);
    // 친구(관계) 조회
    Friend selectById(Long id);
    // 상태 변경
    void updateStatus(Friend friend);
    //친구 요청 조회
    List<FriendRequestListResponse> selectByAccId(Long accId);  
    //친구 목록 조회
    List<FriendListResponse> selectFriendsByMid(Long mid);
    //친구 삭제
    void delete(Long id);
}
