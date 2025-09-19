package com.example.demo.dto.diary;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Diary {

  private Long diaryId;
  private Long userId;
  private String title;
  private String content;

  private Visibility visibility;

  private Emo emo;

  private Weather weather;
  private LocalDateTime createdAt; // DB에 저장되는 타입은 TIMESTAMP WITH TIME ZONE인데 DTO에서의 타입을 OffsetDateTime을 권장한다 만약에
                                   // 단순히 시간만 필요하다면 LocalDateTime도 무방하다.
  private LocalDateTime updateAt;

}
