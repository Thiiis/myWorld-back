package com.example.demo.jukebox.dto;

import lombok.Data;

@Data
public class Track {
  private Long trid;
  private Long jid;
  private Long sid;
  private Long trackOrder;

  public Track(Long jid, Long sid, Long trackOrder) {
    this.jid = jid;
    this.sid = sid;
    this.trackOrder = trackOrder;
  }

}
