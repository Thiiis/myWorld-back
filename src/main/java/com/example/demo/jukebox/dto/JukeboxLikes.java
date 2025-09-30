package com.example.demo.jukebox.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
