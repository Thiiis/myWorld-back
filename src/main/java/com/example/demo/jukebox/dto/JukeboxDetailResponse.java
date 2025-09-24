package com.example.demo.jukebox.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public class JukeboxDetailResponse {
  private Long jid;
  private String title;
  private String content;
  private Date createdAt;
  private Date updatedAt;

  public JukeboxDetailResponse(Long jid, String title, String content, Date createdAt, Date updatedAt) {
    this.jid = jid;
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

}
