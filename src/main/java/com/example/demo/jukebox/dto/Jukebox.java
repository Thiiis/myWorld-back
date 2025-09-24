package com.example.demo.jukebox.dto;

import java.time.OffsetDateTime;

import lombok.Getter;

@Getter
public class Jukebox {
  private Long jid;
  private Long mid;
  private String title;
  private String content;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  // jukebox create 생성자
  public Jukebox(Long mid, String title, String content) {
    this.mid = mid;
    this.title = title;
    this.content = content;
  }

  // jukebox update 생성자
  // new Jukebox(jid, title, content, true)
  public Jukebox(Long jid, String title, String content, boolean forUpdate) { 
    this.jid = jid;
    this.title = title;
    this.content = content;
    this.updatedAt = OffsetDateTime.now();
  }
}
