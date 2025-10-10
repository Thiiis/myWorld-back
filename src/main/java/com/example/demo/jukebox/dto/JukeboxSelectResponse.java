package com.example.demo.jukebox.dto;

import lombok.Data;

@Data
public class JukeboxSelectResponse {
  private Long jid;
  private String title;

  public JukeboxSelectResponse(Long jid, String title) {
    this.jid = jid;
    this.title = title;
  }
}
