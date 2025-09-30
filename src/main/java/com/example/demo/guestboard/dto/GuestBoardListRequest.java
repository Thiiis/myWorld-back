package com.example.demo.guestboard.dto;

import lombok.Data;

@Data
public class GuestBoardListRequest {
  private Long offset;
  private Long limit;

  public GuestBoardListRequest(Long mid, Long offset, Long limit) {
    this.offset = offset;
    this.limit = limit;
  }
}
