package com.example.demo.jukebox.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JukeboxCreateRequest {
  private String title;
  private String content;
}
