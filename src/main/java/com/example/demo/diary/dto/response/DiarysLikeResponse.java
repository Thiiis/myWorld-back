package com.example.demo.diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiarysLikeResponse {
 
  private int likes;        // 총 좋아요 수
  private boolean likedByMe; // 현재 로그인 사용자가 좋아요 눌렀는지 여부
}
