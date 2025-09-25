package com.example.demo.friend.dto;

import java.util.Date;
import com.example.demo.common.dto.ProfileInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendRequestListResponse {
  private Long fid;
  private ProfileInfo requester;
  private Date createdAt;
 
  public FriendRequestListResponse(Long id, ProfileInfo requester, Date createdAt) {
    this.fid = id;
    this.requester = requester;
    this.createdAt = createdAt;
  }
}
