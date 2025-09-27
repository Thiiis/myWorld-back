package com.example.demo.friend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.friend.dao.FriendDao;
import com.example.demo.friend.dto.Friend;
import com.example.demo.friend.dto.FriendCreateRequest;
import com.example.demo.friend.dto.FriendCreateResponse;
import com.example.demo.friend.dto.FriendListResponse;
import com.example.demo.friend.dto.FriendRequestListResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FriendService {

  private final FriendDao friendDao;

  //memeber 구현 완료 후 수정
  public FriendCreateResponse createFriend(FriendCreateRequest dto) {
    Friend friend = new Friend(dto.getReqId(), dto.getAccId());
    friendDao.insert(friend);
    Friend dbFriend = friendDao.selectById(friend.getFid());
    return new FriendCreateResponse(dbFriend.getFid(), dbFriend.getReqId(), dbFriend.getAccId(), dbFriend.getCreatedAt());
    // return new FriendResponse(friend);
  }

  public void acceptFriend(Long id) {
    Friend friend = friendDao.selectById(id);
    friend.accept();
    friendDao.updateStatus(friend);
  }

  public void rejectFriend(Long id) {
    Friend friend = friendDao.selectById(id);
    friend.reject();
    friendDao.updateStatus(friend);
  }

  //memeber 구현 완료 후 수정
  public List<FriendRequestListResponse> getFriendRequestList(Long mid) {
    return friendDao.selectByAccId(mid);
  }

  public List<FriendListResponse> getFriendList(Long mid) {
    return friendDao.selectFriendsByMid(mid);
  }

  public void deleteFriend(Long id) {
    Friend friend = friendDao.selectById(id);
    //검증 로직 추가 필요
    friendDao.delete(id);
  }

}
