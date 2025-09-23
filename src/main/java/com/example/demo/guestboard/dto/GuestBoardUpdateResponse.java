package com.example.demo.guestboard.dto;

import java.time.OffsetDateTime;

import lombok.Getter;

@Getter
public class GuestBoardUpdateResponse {
  private Long gbid;
  private String content;
  private String viewScope;
  private OffsetDateTime updatedAt;

  public GuestBoardUpdateResponse(GuestBoard guestBoard) {
    this.gbid = guestBoard.getGbid();
    this.content = guestBoard.getContent();
    this.viewScope = guestBoard.getViewScope();
    this.updatedAt = guestBoard.getUpdatedAt();
  }
}
