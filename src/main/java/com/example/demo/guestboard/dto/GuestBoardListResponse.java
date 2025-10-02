package com.example.demo.guestboard.dto;

import java.util.Date;

import lombok.Data;

@Data
public class GuestBoardListResponse {
  private Long gbid;
  private Long gid;
  private String nickname;
  private String content;
  private Date createdAt;
  private Date updatedAt;

  public GuestBoardListResponse(Long gbid, Long gid, String nickname, String content, Date createdAt, Date updatedAt) {
    this.gbid = gbid;
    this.gid = gid;
    this.nickname = nickname;
    this.content = content;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
