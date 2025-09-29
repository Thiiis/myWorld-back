package com.example.demo.guestboard.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public class GuestBoardCreateResponse {
  private String content;
  private String nickname;
  private Date createdAt;

  public GuestBoardCreateResponse(String content, String nickname, Date createdAt) {
    this.content = content;
    this.nickname = nickname;
    this.createdAt = createdAt;
  }
}
