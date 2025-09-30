package com.example.demo.jukebox.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Jukebox {
  private Long jid;
  private Long mid;
  private String title;
  private String content;
  private Date createdAt;
  private Date updatedAt;

  // jukebox create 생성자
  public Jukebox(Long mid, String title, String content) {
    this.mid = mid;
    this.title = title;
    this.content = content;
  }

  // jukebox update 생성자
  // new Jukebox(jid, title, content, true)
  public Jukebox(Long jid, String title, String content, boolean forUpdate) { 
    this.jid = jid;
    this.title = title;
    this.content = content;
  }
}
