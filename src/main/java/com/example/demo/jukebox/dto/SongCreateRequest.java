package com.example.demo.jukebox.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SongCreateRequest {
  private String videoId;
}
