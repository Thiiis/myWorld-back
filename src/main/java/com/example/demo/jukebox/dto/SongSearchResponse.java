package com.example.demo.jukebox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SongSearchResponse {
  private String videoId;
  private String title;
  private String artist;
  private String thumbnailurl;

  public SongSearchResponse(String videoId, String title, String artist, String thumbnailurl) {
    this.videoId = videoId;
    this.title = title;
    this.artist = artist;
    this.thumbnailurl = thumbnailurl;
  }
}
