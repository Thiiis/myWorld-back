package com.example.demo.friend.dto;

import java.time.OffsetDateTime;

public class FriendResponse {
  private Long id;
  private Long requesterId;
  private Long accepterId;
  private OffsetDateTime createdAt;

  public FriendResponse(Long id, Long requesterId, Long accepterId, OffsetDateTime createdAt) {
    this.id = id;
    this.requesterId = requesterId;
    this.accepterId = accepterId;
    this.createdAt = createdAt;
  }

  // public FriendResponse(Friend friend) {
  //   this.id = friend.getId();
  //   this.requesterId = friend.getRequesterId();
  //   this.accepterId = friend.getAccepterId();
  //   this.createdAt = friend.getCreatedAt();
  // }
}
