package com.example.demo.jukebox.dto;

import lombok.Getter;

@Getter
public class TrackListResponse {
  private String title;
  private String artist;

  public TrackListResponse(String title, String artist) {
    this.title = title;
    this.artist = artist;
  }
}
