package com.example.demo.diary.dto;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Attachments {

  private Long aid;
  private Long did;
  private String aname;
  private String atype;
  private byte[] adata; //파일 데이터(BLOB) // 다운로드 금지 정책으로 응답에서는 제외하고 업로드 시에만 사용
  private Date createdAt;
  private Date updatedAt;


  // !!! updateDiary 메서드에서 호출하는 생성자 (4개 인자)를 명시적으로 추가 !!!
    public Attachments(Long did, String aname, String atype, byte[] adata) {
        this.did = did;
        this.aname = aname;
        this.atype = atype;
        this.adata = adata;
        // aid, createdAt, updatedAt은 DB나 Service에서 처리될 것으로 가정하고 생략
    }
}