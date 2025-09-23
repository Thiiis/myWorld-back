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

  public Jukebox(JukeboxCreateRequest request) {
    this.mid = request.getMid();
    this.title = request.getTitle();
    this.content = request.getContent();
  }
}
