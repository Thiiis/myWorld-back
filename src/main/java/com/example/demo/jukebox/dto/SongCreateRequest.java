package com.example.demo.jukebox.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SongCreateRequest {
  private Long mid; // 로그인 어노테이션 붙이면 삭제할 예정
  private String videoId;
}
