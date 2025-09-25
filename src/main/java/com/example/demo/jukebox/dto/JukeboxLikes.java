package com.example.demo.jukebox.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JukeboxLikes {
  private Long jlid;
  private Long jid;
  private Long mid;
  private Date createdAt;

  public JukeboxLikes(Long jid, Long mid) {
    this.jid = jid;
    this.mid = mid;
  }
}
