package com.example.demo.diary.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryCommentUpdateRequest {
  private Long did;
  private String content;

  public DiaryCommentUpdateRequest(Long did, String content) {
    this.did = did;
    this.content = content;
  }
}
