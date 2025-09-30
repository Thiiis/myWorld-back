package com.example.demo.guestboard.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestBoard {
  private Long gbid;
  private Long hostid;
  private Long gid;
  private String content;
  private Date createdAt;
  private Date updatedAt;

  // board create 생성자
  public GuestBoard(Long hostid, Long gid, String content) {
    this.hostid = hostid;
    this.gid = gid;
    this.content = content;
  }

  // board update 생성자
  public GuestBoard(Long gbid, String content) {
    this.gbid = gbid;
    this.content = content;
  }

}
