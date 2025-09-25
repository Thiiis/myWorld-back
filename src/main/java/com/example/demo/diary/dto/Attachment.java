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

  //응답 오버로딩
  public Attachment(Long did, String aname, String atype, byte[] adata) {
    this.did = did;
    this.aname = aname;
    this.atype = atype;
    this.adata = adata;
  }

  // 방법 2. 생성자 오버로딩 방식
  public Attachment(Long aid, Long did, String aname, String atype, byte[] adata, Date createdAt, Date updatedAt) {
    this.aid = aid;
    this.did = did;
    this.aname = aname;
    this.atype = atype;
    this.adata = adata;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

    // 정적 팩토리 메서드 - 생성 시점
    //public static

  // --- 도메인 행위 메서드 --- entity에서는 update필드명메소드 스타일로 두는것이 더 좋다.(방법2.메소드 개별로 생성)
    public void updateAid(Long aid) {
        this.aid = aid;
    }

    public void updateDid(Long did) {
        this.did = did;
    }

    public void updateAname(String aname) {
        this.aname = aname;
    }

    public void updateAtype(String atype) {
        this.atype = atype;
    }

    public void updateAdata(byte[] adata) {
        this.adata = adata;
    }
}