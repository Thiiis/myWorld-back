package com.example.demo.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileInfo {
  private Long mid;
  private String account;
  private String nickname;
  private String birthdate;
  private String imgUrl;
  private String statusMessage;

  public ProfileInfo(Long mid, String nickname, String birthdate, String imgUrl, String statusMessage) {
    this.mid = mid;
    this.nickname = nickname;
    this.birthdate = birthdate;
    this.imgUrl = imgUrl;
    this.statusMessage = statusMessage;
  }
}
