package com.example.demo.diary.dto;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Attachment {
  // 클라이언트 <-- 서버 (DB에서 자동 생성)
  private Long aid;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  // 클라이언트 <--> 서버 (파일 업로드 시 요청, 응답에도 포함 가능)
  private Long did;
  private String aname;
  private String atype;

  // 클라이언트 --> 서버 (업로드 시점에만 필요, 응답에는 내려주지 않음)
  private byte[] adata; //파일 데이터(BLOB) // 다운로드 금지 정책으로 응답에서는 제외하고 업로드 시에만 사용

  // ---MyBaits 매핑 전용 Setter --
  public void setAid(Long aid) { this.aid = aid; }
  public void setDid(Long did) { this.did = did; }
  public void setAname(String aname) { this.aname = aname; }
  public void setAtype(String atype) { this.atype = atype; }
  public void setAdata(byte[] adata) { this.adata = adata; }
  public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
  public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}