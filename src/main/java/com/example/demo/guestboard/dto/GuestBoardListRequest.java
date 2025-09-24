package com.example.demo.guestboard.dto;

import lombok.Getter;

@Getter
public class GuestBoardListRequest {
  private Long offset;
  private Long limit;

  public GuestBoardListRequest(Long offset, Long limit) {
    this.offset = offset;
    this.limit = limit;
  }
}
