package com.example.demo.guestboard.dto;

import lombok.Getter;

@Getter
public class GuestBoardCreateRequest {
  private String content;
  private String viewScope;
}
