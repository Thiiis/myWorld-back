package com.example.demo.jukebox.dto;

import lombok.Data;

@Data
public class TrackListResponse {
  private Long trid;
  private Long sid;
  private String title;
  private String artist;
  private Long duration;
  private String videoId;

  public TrackListResponse(Long trid, Long sid, String title, String artist, Long duration, String videoId) {
    this.trid = trid;
    this.sid = sid;
    this.title = title;
    this.artist = artist;
    this.duration = duration;
    this.videoId = videoId;
  }
}
