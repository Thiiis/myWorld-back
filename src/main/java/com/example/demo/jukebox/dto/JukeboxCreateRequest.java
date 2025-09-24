package com.example.demo.jukebox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JukeboxCreateRequest {
  private Long mid;
  private String title;
  private String content;
}
