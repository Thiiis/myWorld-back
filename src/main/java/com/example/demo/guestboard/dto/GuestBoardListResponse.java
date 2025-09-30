package com.example.demo.guestboard.dto;

import java.util.Date;

import lombok.Data;

@Data
public class GuestBoardListResponse {
  private Long gbid;
  private Long gid;
  private String content;
  private String viewScope;
  private Date createdAt;
  private Date updatedAt;

  public GuestBoardListResponse(Long gbid, Long gid, String content, String viewScope, Date createdAt, Date updatedAt) {
    this.gbid = gbid;
    this.gid = gid;
    this.content = content;
    this.viewScope = viewScope;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
