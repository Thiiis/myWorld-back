package com.example.demo.guestboard.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public class GuestBoardCreateResponse {
  private Long gbid;
  private Date createdAt;
  private Date updatedAt;

  public GuestBoardCreateResponse(Long gbid, Date createdAt, Date updatedAt) {
    this.gbid = gbid;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
