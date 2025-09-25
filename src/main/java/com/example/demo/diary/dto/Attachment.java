package com.example.demo.diary.dto;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Attachment {

  private Long aid;
  private Long did;
  private String aname;
  private String atype;
  private byte[] adata; //파일 데이터(BLOB) // 다운로드 금지 정책으로 응답에서는 제외하고 업로드 시에만 사용
  private Date createdAt;
  private Date updatedAt;

 // 업로드용 (insert 시점)
    public Attachment(Long did, String aname, String atype, byte[] adata) {
        this.did = did;
        this.aname = aname;
        this.atype = atype;
        this.adata = adata;
    }

    // 조회용 (select 결과)
    public Attachment(Long aid, Long did, String aname, String atype, byte[] adata, Date createdAt, Date updatedAt) {
        this.aid = aid;
        this.did = did;
        this.aname = aname;
        this.atype = atype;
        this.adata = adata;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 수정용
    public Attachment(Long aid, Long did, String aname, String atype, byte[] adata) {
        this.aid = aid;
        this.did = did;
        this.aname = aname;
        this.atype = atype;
        this.adata = adata;
    }
}