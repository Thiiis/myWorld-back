package com.example.demo.guestboard.dto;

import java.time.OffsetDateTime;

import lombok.Getter;

@Getter
public class GuestBoard {
  private Long gbid;
  private Long gid;
  private Long pid;
  private String content;
  private String viewScope;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public GuestBoard() {}

  public GuestBoard(GuestBoardCreateRequest request) {
    this.gid = request.getGid();
    this.pid = request.getPid();
    this.content = request.getContent();
    this.viewScope = request.getViewScope();
  }

  public GuestBoard(GuestBoardUpdateRequest request) {
    this.content = request.getContent();
    this.viewScope = request.getViewScope();
  }
}
