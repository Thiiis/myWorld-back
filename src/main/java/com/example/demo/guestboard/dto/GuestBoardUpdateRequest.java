package com.example.demo.guestboard.dto;

import lombok.Getter;

@Getter
public class GuestBoardUpdateRequest {
  private String gbid;
  private String content;
  private String viewScope;
}
