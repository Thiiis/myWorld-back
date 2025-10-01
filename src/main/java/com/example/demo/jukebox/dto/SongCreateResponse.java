package com.example.demo.jukebox.dto;

import lombok.Data;

@Data
public class SongCreateResponse {
  private Long sid;
  private String title;
  private String artist;
  private String videoId;

  public SongCreateResponse(Long jid, Long sid, String title, String artist, String videoId) {
    this.sid = sid;
    this.title = title;
    this.artist = artist;
    this.videoId = videoId;
  }
}
