package com.example.demo.diary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.diary.dto.Attachment;

@Mapper
public interface AttachmentDao {
  int insertAttachment(Attachment attachment);            // 첨부파일 저장
  Long selectInsertedAttachmentId(Attachment attachment); // insert시 마지막 AID select로 가져와서 클라이언트에 전달 

  //List<Attachment> selectAttachmentsByDid(Long did);   // 특정 일기 첨부 전체 조회
  //Attachment selectFirstAttachmentByDid(Long did);     // 대표사진(첫 번째 첨부) 조회
  //int deleteAttachmentsByDid(Long did);                // 특정 일기 첨부 전체 삭제
  
}
