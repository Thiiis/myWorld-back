package com.example.demo.friend.dto;

import java.util.Date;

import com.example.demo.common.dto.ProfileInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendListResponse {
  private Long fid;
  private ProfileInfo friendInfo;
  private Date createdAt;

  public FriendListResponse(Long fid, ProfileInfo friendInfo, Date createdAt) {
    this.fid = fid;
    this.friendInfo = friendInfo;
    this.createdAt = createdAt;
  }
}
