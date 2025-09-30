package com.example.demo.jukebox.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JukeboxCreateRequest {
  private Long mid;
  private String title;
  private String content;
}
