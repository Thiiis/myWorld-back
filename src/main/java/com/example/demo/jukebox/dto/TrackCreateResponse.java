package com.example.demo.jukebox.dto;

import lombok.Data;

@Data
public class TrackCreateResponse {
  private Long trid;
  private Long jid;
  private Long sid;
  
  public TrackCreateResponse(Long trid, Long jid, Long sid) {
    this.trid = trid;
    this.jid = jid;
    this.sid = sid;
  }
}
