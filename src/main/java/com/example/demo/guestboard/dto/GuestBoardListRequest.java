package com.example.demo.guestboard.dto;

import lombok.Data;

@Data
public class GuestBoardListRequest {
  private Long hostid;
  private Long offset;
  private Long limit;

  public GuestBoardListRequest(Long hostid, Long offset, Long limit) {
    this.hostid = hostid;
    this.offset = offset;
    this.limit = limit;
  }
}
