package com.example.demo.friend.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendCreateResponse {
  private Long fid;
  private Long reqId;
  private Long accId;
  private Date createdAt;

    public FriendCreateResponse(Long fid, Long reqId, Long accId, Date createdAt) {
      this.fid = fid;
      this.reqId = reqId;
      this.accId = accId;
      this.createdAt = createdAt;
    }

  // public FriendResponse(Friend friend) {
  //   this.fid = friend.getFid();
  //   this.requesterId = friend.getRequesterId();
  //   this.accepterId = friend.getAccepterId();
  //   this.createdAt = OffsetDateTime.now();
  // }
}
