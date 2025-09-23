package com.example.demo.guestboard.dto;

import java.time.OffsetDateTime;

import lombok.Getter;

@Getter
public class GuestBoardListResponse {
  private Long gbid;
  private Long gid;
  private String content;
  private String viewScope;
  private OffsetDateTime updatedAt;

  public GuestBoardListResponse(GuestBoard guestBoard) {
    this.gbid = guestBoard.getGbid();
    this.gid = guestBoard.getGid();
    this.content = guestBoard.getContent();
    this.viewScope = guestBoard.getViewScope();
    this.updatedAt = guestBoard.getUpdatedAt();
  }
}
