package com.example.demo.jukebox.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class JukeboxDetailResponse {
  private Long jid;
  private String title;
  private String content;
  private Date createdAt;
  private Date updatedAt;
  private int trackCount;
  private Long totalDuration;
  private List<TrackListResponse> songs;

  public JukeboxDetailResponse(Long jid, String title, String content, Date createdAt, Date updatedAt, Long totalDuration, int trackCount, List<TrackListResponse> songs) {
    this.jid = jid;
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.totalDuration = totalDuration;
    this.trackCount = trackCount;
    this.songs = songs;
  }

}
