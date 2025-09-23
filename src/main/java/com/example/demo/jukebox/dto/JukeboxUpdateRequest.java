package com.example.demo.jukebox.dto;

import lombok.Getter;

@Getter
public class JukeboxUpdateRequest {
  private Long jid;
  private String title;
  private String content;
}
