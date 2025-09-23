package com.example.demo.jukebox.dto;

import java.time.OffsetDateTime;

public class Song {
  private Long sid;
  private Long jid;
  private String title;
  private String artist;
  private String songUrl;
  private Long duration;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
}
