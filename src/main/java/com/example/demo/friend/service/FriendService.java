package com.example.demo.friend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.dto.ProfileInfo;
import com.example.demo.friend.dao.FriendDao;
import com.example.demo.friend.dto.Friend;
import com.example.demo.friend.dto.FriendCreateRequest;
import com.example.demo.friend.dto.FriendCreateResponse;
import com.example.demo.friend.dto.FriendListResponse;
import com.example.demo.friend.dto.FriendRequestListResponse;
import com.example.demo.profile.dto.Profile;

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
  public List<FriendRequestListResponse> getFriednRequestList(Long userId) {
    return friendDao.selectByAccepterId(userId);
  }

  public List<FriendListResponse> getFriendList(Long mid) {
    List<Friend> friends = friendDao.selectFriendsByMid(mid);
    return friends.stream().map(friend -> {
            // userId가 requester인지 accepter인지에 따라 상대방 정보 가져오기
            Long friendMid = friend.getReqId().equals(mid) ? 
                               friend.getAccId() : friend.getReqId();
            
            // 친구의 프로필 정보 가져오기 (임시)
            Profile friendProfile = findUserByIdTemporarily(friendMid);
            ProfileInfo friendInfo = new ProfileInfo(
                friendProfile.getPid(),
                friendProfile.getNickname(),
                friendProfile.getImgUrl(),
                friendProfile.getStatusMessage()
            );
               return new FriendListResponse(friend.getFid(), friendInfo, friend.getCreatedAt());
        }).collect(Collectors.toList());
  }      
}
