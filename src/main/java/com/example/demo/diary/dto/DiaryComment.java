// // package com.example.demo.diary.dto;

// // import java.util.Date;

// import lombok.Getter;
// import lombok.NoArgsConstructor;

// @Getter
// @NoArgsConstructor
// public class DiaryComment {

//   private Long dcid;
//   private Long did;
//   private Long mid;
//   private Long parentDcid;
//   private String content;
//   private Date createAt;
//   private Date updateAt;

//   //생성용
//   public DiaryComment(Long did, Long mid, Long parentDcid, String content) {
//     this.did = did;
//     this.mid = mid;
//     this.parentDcid = parentDcid;
//     this.content = content;
//   }

//   //읽기용
//   public DiaryComment(Long dcid, Long did, Long mid, Long parentDcid, String content, Date createAt, Date updateAt) {
//     this.dcid = dcid;
//     this.did = did;
//     this.mid = mid;
//     this.parentDcid = parentDcid;
//     this.content = content;
//     this.createAt = createAt;
//     this.updateAt = updateAt;
//   }

//   //수정용
//   public DiaryComment(Long did, Long dcid, String content) {
//     this.did = did;
//     this.dcid = dcid;
//     this.content = content;
//   }

// }
