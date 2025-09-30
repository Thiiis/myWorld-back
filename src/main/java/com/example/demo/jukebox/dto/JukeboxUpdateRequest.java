package com.example.demo.jukebox.dto;

import lombok.Data;

@Data
public class JukeboxUpdateRequest {
  private Long jid;
  private String title;
  private String content;
}
