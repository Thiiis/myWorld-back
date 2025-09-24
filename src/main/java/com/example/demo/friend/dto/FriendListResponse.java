package com.example.demo.friend.dto;

import java.time.OffsetDateTime;

import com.example.demo.common.dto.ProfileInfo;

import lombok.Getter;

@Getter
public class FriendListResponse {
  private Long fid;
  private ProfileInfo friendInfo;
  private OffsetDateTime createdAt;

  public FriendListResponse(Long fid, ProfileInfo friendInfo, OffsetDateTime createdAt) {
    this.fid = fid;
    this.friendInfo = friendInfo;
    this.createdAt = createdAt;
  }
}
