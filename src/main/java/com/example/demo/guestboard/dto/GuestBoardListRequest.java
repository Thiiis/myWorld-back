package com.example.demo.guestboard.dto;

import lombok.Getter;

@Getter
public class GuestBoardListRequest {
  private Long offset = 0L;
  private Long limit = 10L;
}
