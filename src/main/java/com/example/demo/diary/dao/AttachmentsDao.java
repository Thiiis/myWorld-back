package com.example.demo.diary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.diary.dto.Attachments;

// 테이블 안에 컬럼을 CRUD할지, 아니면 컬럼을 CREATE&DELETE만 할지
@Mapper
public interface AttachmentsDao {
  int insert(Attachments attachment);                // 첨부파일 저장
  
  Attachments selectAttachByAid(Long aid);           // 사진 단일 조회
  List<Attachments> selectAttachByDid(Long did);     // 사진 리스트 조회
  Attachments selectThumbnailByDid(Long did);        // 대표사진(첫 번째 첨부) 조회

  int update(Attachments attachment);                // 첨부파일 수정(aid 기준으로 이름/타입/데이터 갱신)

  int delete(Long aid);                              // 단일 첨부파일 단일삭제
  int deleteByDid(Long did);                         // 특정일기 다중파일 삭제
  int deleteByDids(@Param("list") List<Long> dids);  // 다중일기 첨부파일 삭제
}
