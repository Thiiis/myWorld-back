package com.example.demo.jukebox.dto;

import java.time.OffsetDateTime;

import lombok.Getter;

@Getter
public class JukeboxCreateResponse {
  private Long jid;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public JukeboxCreateResponse(Jukebox jukebox) {
    this.jid = jukebox.getJid();
    this.createdAt = OffsetDateTime.now();
    this.updatedAt = OffsetDateTime.now();
  }
}
