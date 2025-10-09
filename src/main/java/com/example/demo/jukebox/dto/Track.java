package com.example.demo.jukebox.dto;

import lombok.Data;

@Data
public class Track {
  private Long trid;
  private Long jid;
  private Long sid;

  public Track(Long jid, Long sid) {
    this.jid = jid;
    this.sid = sid;
  }

}
