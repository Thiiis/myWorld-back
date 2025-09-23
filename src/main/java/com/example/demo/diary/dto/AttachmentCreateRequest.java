// package com.example.demo.diary.dto;

// import lombok.Getter;

// @Getter
// public class AttachmentCreateRequest {
//   //private Long did; //서버에서 만들어주기 때문에 요청받을 때 did가 없어도 된다.
//   private String aname;
//   private String atype;

//   // 클라이언트 --> 서버 (업로드 시점에만 필요, 응답에는 내려주지 않음)
//   private byte[] adata; //파일 데이터(BLOB) // 다운로드 금지 정책으로 응답에서는 제외하고 업로드 시에만 사용

//   public AttachmentCreateRequest() {}
//   public AttachmentCreateRequest(String aname, String atype) {
//     this.aname = aname;
//     this.atype = atype;
//   }

//   // public void setAname(String aname) { this.aname = aname; }
//   // public void setAtype(String atype) { this.atype = atype; }
//   // public void setAdata(byte[] adata) { this.adata = adata; }
// }
