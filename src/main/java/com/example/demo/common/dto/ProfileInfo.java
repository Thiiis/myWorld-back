package com.example.demo.common.dto;

import lombok.Data;

@Data
public class ProfileInfo {
  private Long pid;
  private String nickname;
  private String imgUrl;
  private String statusMessage;

  public ProfileInfo(Long pid, String nickname, String imgUrl, String statusMessage) {
    this.pid = pid;
    this.nickname = nickname;
    this.imgUrl = imgUrl;
    this.statusMessage = statusMessage;
  }
}
