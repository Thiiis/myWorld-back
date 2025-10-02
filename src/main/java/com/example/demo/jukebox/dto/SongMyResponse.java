package com.example.demo.jukebox.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SongMyResponse {
  private String title;
  private String artist;
  private Long duration;

  public SongMyResponse(String title, String artist, Long duration) {
    this.title = title;
    this.artist = artist;
    this.duration = duration;
  }
}
