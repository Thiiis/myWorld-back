package com.example.demo.friend.dto;

import java.time.OffsetDateTime;

import com.example.demo.common.dto.ProfileInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendCreateResponse {
  private Long fid;
  private ProfileInfo accepter;
  private OffsetDateTime createdAt;

  public FriendCreateResponse(Long id, ProfileInfo accepter, OffsetDateTime createdAt) {
    this.fid = id;
    this.accepter = accepter;
    this.createdAt = createdAt;
  }

    // public FriendResponse(Long fid, Long requesterId, Long accepterId, OffsetDateTime createdAt) {
    //   this.fid = fid;
    //   this.requesterId = requesterId;
    //   this.accepterId = accepterId;
    //   this.createdAt = createdAt;
    // }

  // public FriendResponse(Friend friend) {
  //   this.fid = friend.getFid();
  //   this.requesterId = friend.getRequesterId();
  //   this.accepterId = friend.getAccepterId();
  //   this.createdAt = OffsetDateTime.now();
  // }
}
