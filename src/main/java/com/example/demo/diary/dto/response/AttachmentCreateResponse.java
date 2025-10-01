package com.example.demo.diary.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttachmentCreateResponse {

  private Long aid;
  private String aname;
  private String atype;
  private String url;   //클라이언트에서 접근할 수 있는 경로(장점:aid,파일명,미리보기 url 등)-원본데이터를 보내주면 용량이 클 때 성능저하 발생하기 때문
  
  public AttachmentCreateResponse(Long aid, String aname, String atype, String url) {
    this.aid = aid;
    this.aname = aname;
    this.atype = atype;
    this.url = url;
  }
}
