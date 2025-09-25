package com.example.demo.jukebox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TrackCreateRequest {
  private Long jid;
  private Long sid;
  private Long trackOrder;
}
