package com.example.demo.friend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.AccessDeniedException;
import com.example.demo.exception.ResourceNotFoundeException;
import com.example.demo.friend.dao.FriendDao;
import com.example.demo.friend.dto.Friend;
import com.example.demo.friend.dto.FriendCreateResponse;
import com.example.demo.friend.dto.FriendListResponse;
import com.example.demo.friend.dto.FriendRequestListResponse;
import com.example.demo.friend.enums.FriendStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendService {

  private final FriendDao friendDao;

  //memeber 구현 완료 후 수정
  @Transactional
  public FriendCreateResponse createFriend(Long reqId, Long accId) {
    if (reqId.equals(accId)) {
      throw new IllegalArgumentException("자기 자신에게 친구 요청을 할 수 없습니다.");
    }
    Friend existing =  friendDao.selectByMembers(reqId, accId);
    if (existing != null) { //대기인경우 수락인경우 나눠서 예외처리
      throw new IllegalStateException("이미 친구 관계입니다.");
    } 
    Friend friend = new Friend(reqId, accId);
    friendDao.insert(friend); //fid 존재하는지 예외처리
    Friend dbFriend = friendDao.selectById(friend.getFid());
    return new FriendCreateResponse(dbFriend.getFid(), dbFriend.getReqId(), dbFriend.getAccId(), dbFriend.getCreatedAt());
    // return new FriendResponse(friend);
  }

  @Transactional
  public void acceptFriend(Long fid, Long loginMid) {
    Friend friend = friendDao.selectById(fid);
    if(friend == null) {
      throw new ResourceNotFoundeException("친구 관계를 찾을 수 없습니다.");
    } 
    if (!friend.getAccId().equals(loginMid)) {
        throw new AccessDeniedException("본인만 친구 요청을 수락할 수 있습니다.");
    }
    //이미 수락/거절한 친구 예외처리
    //if (!FriendStatus.PENDING.name().equals(friend.getStatus())) {
    //     throw new IllegalStateException("이미 처리된 친구 요청입니다.");
    // }
    friend.setStatus(FriendStatus.ACCEPTED.name());
    friendDao.updateStatus(friend);
  }

  @Transactional
  public void rejectFriend(Long fid, Long loginMid) {
    Friend friend = friendDao.selectById(fid);
    if(friend == null) {
      throw new ResourceNotFoundeException("친구 관계를 찾을 수 없습니다.");
    }
    if (!friend.getAccId().equals(loginMid)) {
        throw new AccessDeniedException("본인만 친구 요청을 거절할 수 있습니다.");
    }
    friend.setStatus(FriendStatus.REJECTED.name());
    friendDao.updateStatus(friend);
  }

  public List<FriendRequestListResponse> getFriendRequestList(Long loginMid) {
    return friendDao.selectByAccId(loginMid);
  }

  public List<FriendListResponse> getFriendList(Long mid) {
    return friendDao.selectFriendsByMid(mid);
  }

  @Transactional
  public void deleteFriend(Long fid, Long loginMid) {
    Friend friend = friendDao.selectById(fid);
    if(friend == null) {
      throw new ResourceNotFoundeException("친구 관계를 찾을 수 없습니다.");
    }
    if(!friend.getReqId().equals(loginMid) && !friend.getAccId().equals(loginMid)) {
      throw new AccessDeniedException("본인만 친구 관계를 끊을 수 있습니다.");
    }
    friendDao.delete(fid);
  }

}
