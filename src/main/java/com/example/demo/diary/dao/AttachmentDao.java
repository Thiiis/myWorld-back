// package com.example.demo.diary.dao;

// import java.util.List;

// import org.apache.ibatis.annotations.Mapper;

// import com.example.demo.diary.dto.Attachment;

// // 테이블 안에 컬럼을 CRUD할지, 아니면 컬럼을 CREATE&DELETE만 할지
// @Mapper
// public interface AttachmentDao {
//   //저장
//   int insert(Attachment attachment);            // 첨부파일 저장
  
//   //조회
//   List<Attachment> selectAttachmentsByDid(Long did);      // 특정 일기 첨부 전체 조회
//   Attachment selectThumbnailByDid(Long did);        // 대표사진(첫 번째 첨부) 조회

//   //수정
//   int update(Attachment attachment);            // 첨부파일 수정(aid 기준으로 이름/타입/데이터 갱신)

//   //삭제
//   //int delete(Long aid);                       // 첨부파일 삭제
//   int delete(Long did);                         //일기 ID로 모든 첨부파일 삭제(일기 삭제용-1개의 ID에 여러개의 첨부파일 삭제)
//   int deleteByDids(List<Long> dids);            //일기 ID 리스트로 모든 첨부파일 삭제 (일기다중 삭제용-여러개의 ID에 여러개의 첨부파일 삭제)
// }
