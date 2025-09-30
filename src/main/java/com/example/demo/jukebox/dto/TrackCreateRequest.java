package com.example.demo.jukebox.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrackCreateRequest {
  private Long jid;
  private Long sid;
  private Long trackOrder;
}
