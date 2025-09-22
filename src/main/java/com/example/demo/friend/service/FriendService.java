package com.example.demo.friend.service;

import org.springframework.stereotype.Service;

import com.example.demo.friend.dao.FriendDao;
import com.example.demo.friend.dto.Friend;
import com.example.demo.friend.dto.FriendCreateRequest;
import com.example.demo.friend.dto.FriendResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendService {

  private final FriendDao friendDao;

  public FriendResponse createFriend(FriendCreateRequest dto) {
    Friend friend = new Friend(dto.getRequesterId(), dto.getAccepterId());
    friendDao.insert(friend);
    return new FriendResponse(friend.getId(), friend.getRequesterId(), friend.getRequesterId(), friend.getCreatedAt());
    // return new FriendResponse(friend);
  }
}
