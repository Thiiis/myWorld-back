package com.example.demo.friend.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendResponse {
  private Long fid;
  private Long requesterId;
  private Long accepterId;
  private OffsetDateTime createdAt;

  // public FriendResponse(Long id, Long requesterId, Long accepterId, OffsetDateTime createdAt) {
  //   this.id = id;
  //   this.requesterId = requesterId;
  //   this.accepterId = accepterId;
  //   this.createdAt = OffsetDateTime.now();
  // }

  public FriendResponse(Friend friend) {
    this.fid = friend.getFid();
    this.requesterId = friend.getRequesterId();
    this.accepterId = friend.getAccepterId();
    this.createdAt = OffsetDateTime.now();
  }
}
