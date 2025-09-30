package com.example.demo.jukebox.dto;

import lombok.Data;

@Data
public class TrackCreateResponse {
  private Long trid;
  private Long jid;
  private Long sid;
  private Long trackOrder;
  
  public TrackCreateResponse(Long trid, Long jid, Long sid, Long trackOrder) {
    this.trid = trid;
    this.jid = jid;
    this.sid = sid;
    this.trackOrder = trackOrder;
  }
}
