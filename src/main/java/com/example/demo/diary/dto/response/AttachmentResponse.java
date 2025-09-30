// package com.example.demo.diary.dto.response;

// import java.util.Date;
// import java.util.List;

// import com.example.demo.diary.dto.Attachment;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class AttachmentResponse {
//   //공통(Create, read)
//   private Long aid;
//   private String aname;
//   private String atype;
  
//   //Create
//   private String url;   //클라이언트에서 접근할 수 있는 경로(장점:aid,파일명,미리보기 url 등)-원본데이터를 보내주면 용량이 클 때 성능저하 발생하기 때문

//   //attachment
//   private List<AttachmentResponse> attachments;   // 사진 목록
//   //private Attachment thumbnail;                 // 썸네일 원본-방법1
//   private AttachmentResponse thumbnail;           // 썸네일 URL-방법2
// }
