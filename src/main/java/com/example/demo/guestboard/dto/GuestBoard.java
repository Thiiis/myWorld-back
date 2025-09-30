package com.example.demo.guestboard.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestBoard {
  private Long gbid;
  private Long gid;
  private Long hostid;
  private String content;
  private String viewScope;
  private Date createdAt;
  private Date updatedAt;

  // board create 생성자
  public GuestBoard(Long gid, Long hostid, String content, String viewScope) {
    this.gid = gid;
    this.hostid = hostid;
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
