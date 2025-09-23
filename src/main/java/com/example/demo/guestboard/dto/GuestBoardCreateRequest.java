package com.example.demo.guestboard.dto;

import lombok.Getter;

@Getter
public class GuestBoardCreateRequest {
  private Long gid;
  private Long pid;
  private String content;
  private String viewScope;

  public GuestBoardCreateRequest() {
  }

  public GuestBoardCreateRequest(Long gid, Long pid, String content, String viewScope) {
    this.gid = gid;
    this.pid = pid;
    this.content = content;
    this.viewScope = viewScope;
  }

}
