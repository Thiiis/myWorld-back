package com.example.demo.jukebox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SongCreateRequest {
  private Long jid;
  private String videoId;
}
