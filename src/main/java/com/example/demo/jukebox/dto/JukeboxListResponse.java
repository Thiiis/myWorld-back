package com.example.demo.jukebox.dto;

import lombok.Data;

@Data
public class JukeboxListResponse {
  private Long jid;
  private String title;

  public JukeboxListResponse(Long jid, String title) {
    this.jid = jid;
    this.title = title;
  }
}
