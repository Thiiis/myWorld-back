package com.example.demo.jukebox.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JukeboxCreateResponse {
  private Long jid;
  private String title;
  private String content;
  private Date createdAt;

  public JukeboxCreateResponse(Long jid, String title, String content, Date createdAt) {
    this.jid = jid;
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
  }
}
