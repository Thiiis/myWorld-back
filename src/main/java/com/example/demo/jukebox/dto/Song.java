package com.example.demo.jukebox.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Song {
  private Long sid;
  private Long mid;
  private String title;
  private String artist;
  private Long duration;
  private String videoId;
  private Date createdAt;

  // Song create 생성자
  public Song(Long mid, String title, String artist, String videoId, Long duration) {
    this.mid = mid;
    this.title = title;
    this.artist = artist;
    this.videoId = videoId;
    this.duration = duration;
  }
}
