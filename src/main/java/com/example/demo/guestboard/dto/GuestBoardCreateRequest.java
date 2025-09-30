package com.example.demo.guestboard.dto;

import lombok.Data;

@Data
public class GuestBoardCreateRequest {
  private String content;
  private String viewScope;
}
