package com.example.demo.guestboard.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestBoard {
  private Long gbid;
  private Long gid;
  private Long pid;
  private String content;
  private String viewScope;
  private Date createdAt;
  private Date updatedAt;

  // board create 생성자
  public GuestBoard(Long gid, Long pid, String content, String viewScope) {
    this.gid = gid;
    this.pid = pid;
    this.content = content;
    this.viewScope = viewScope;
  }

  // board update 생성자
  public GuestBoard(Long gbid, String content, String viewScope) {
    this.gbid = gbid;
    this.content = content;
    this.viewScope = viewScope;
  }

}
