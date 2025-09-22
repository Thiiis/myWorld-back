package com.example.demo.guestboard.dto;

import java.time.OffsetDateTime;

public class GuestBoard {
  private Long gbid;
  private Long gid;
  private Long pid;
  private String content;
  private String view_scope;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public GuestBoard() {
  }

  public GuestBoard(Long gid, Long pid, String content) {
    this.gid = gid;
    this.pid = pid;
    this.content = content;
  }

  public Long getGbid() {
    return gbid;
  }

  public Long getGid() {
    return gid;
  }

  public Long getPid() {
    return pid;
  }

  public String getContent() {
    return content;
  }

  public void setGid(Long gid) {
    this.gid = gid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
}
