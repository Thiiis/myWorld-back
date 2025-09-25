package com.example.demo.diary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.diary.dto.Attachment;
import com.example.demo.diary.dto.request.AttachmentUpdateRequest;

@Mapper
public interface AttachmentDao {
  //저장
  int insert(Attachment attachment);            // 첨부파일 저장
  Long selectInsertedAttachmentId(Attachment attachment); // insert시 마지막 AID select로 가져와서 클라이언트에 전달 - 저장에 사용됨
  
  //조회
  List<Attachment> selectAttachmentsByDid(Long did);      // 특정 일기 첨부 전체 조회
  Attachment selectFeaturedByDid(Long did);        // 대표사진(첫 번째 첨부) 조회

  //수정
  int update(Attachment attachment);            // 첨부파일 수정(aid 기준으로 이름/타입/데이터 갱신)

  //삭제
  int delete(Long aid);                         // 첨부파일 삭제
  //int deleteAttachmentsByDid(Long did);                // 특정 일기 첨부 전체 삭제
  
}
