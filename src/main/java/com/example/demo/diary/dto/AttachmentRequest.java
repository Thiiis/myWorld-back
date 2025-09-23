package com.example.demo.diary.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;

@Getter
public class AttachmentRequest {

   // 그룹 정의
  public interface Create {}
  public interface Update {}
  
  @Null(groups = Create.class, message = "생성 시에는 ID를 넣을 수 없습니다.")      // 생성 시에는 반드시 null이어야 함
  @NotNull(groups = Update.class, message = "수정 시에는 ID가 필요합니다.")
  private Long did; //서버에서 만들어주기 때문에 요청받을 때 did가 없어도 된다.

  private String aname;
  private String atype;

  // 클라이언트 --> 서버 (업로드 시점에만 필요, 응답에는 내려주지 않음)
  private byte[] adata; //파일 데이터(BLOB) // 다운로드 금지 정책으로 응답에서는 제외하고 업로드 시에만 사용

  public AttachmentRequest() {}
  public AttachmentRequest(String aname, String atype) {
    this.aname = aname;
    this.atype = atype;
  }

  // public void setAname(String aname) { this.aname = aname; }
  // public void setAtype(String atype) { this.atype = atype; }
  // public void setAdata(byte[] adata) { this.adata = adata; }
}
