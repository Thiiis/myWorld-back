package com.example.demo.jukebox.dto;

import lombok.Getter;

@Getter
public class JukeboxCreateRequest {
  private Long mid;
  private String title;
  private String content;

  JukeboxCreateRequest() {}
}
