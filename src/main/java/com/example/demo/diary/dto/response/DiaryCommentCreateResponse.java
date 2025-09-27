package com.example.demo.diary.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryCommentCreateResponse {
  private Long dcid;
  private Long did;
  private Long mid;
  private Long parentDcid;
  private String content;
  private Date createAt;
  private Date updateAt;

  // 생성용
  public DiaryCommentCreateResponse(Long dcid, Long did, Long mid, Long parentDcid, String content, Date createAt) {
    this.dcid = dcid;
    this.did = did;
    this.mid = mid;
    this.parentDcid = parentDcid;
    this.content = content;
    this.createAt = createAt;
  }

  public DiaryCommentCreateResponse(Long dcid, Long did, Long mid, Long parentDcid, String content, Date createAt,
      Date updateAt) {
    this.dcid = dcid;
    this.did = did;
    this.mid = mid;
    this.parentDcid = parentDcid;
    this.content = content;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }

  //수정용
  
}
