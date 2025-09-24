package com.example.demo.guestboard.dto;

import lombok.Getter;

@Getter
public class GuestBoardCreateRequest {
  private Long gid;
  private Long pid;
  private String content;
  private String viewScope;
}
