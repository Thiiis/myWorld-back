package com.example.demo.guestboard.dto;

import lombok.Data;

@Data
public class GuestBoardUpdateRequest {
  private Long gbid;
  private String content;
  private String viewScope;
}
