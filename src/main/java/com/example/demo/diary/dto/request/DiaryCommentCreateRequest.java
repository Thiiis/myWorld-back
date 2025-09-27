package com.example.demo.diary.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryCommentCreateRequest {

  private Long did;
  private Long mid;
  private Long parentDcid;
  private String content;

  //생성용
  public DiaryCommentCreateRequest(Long did, Long mid, Long parentDcid, String content) {
    this.did = did;
    this.mid = mid;
    this.parentDcid = parentDcid;
    this.content = content;
  }

}
