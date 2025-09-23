package com.example.demo.guestboard.dto;

import java.time.OffsetDateTime;

import lombok.Getter;

@Getter
public class GuestBoardCreateResponse {
  private Long gbid;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public GuestBoardCreateResponse() {}

  public GuestBoardCreateResponse(GuestBoard guestBoard) {
    this.gbid = guestBoard.getGbid();
    this.createdAt = OffsetDateTime.now();
    this.updatedAt = OffsetDateTime.now();
  }
}
