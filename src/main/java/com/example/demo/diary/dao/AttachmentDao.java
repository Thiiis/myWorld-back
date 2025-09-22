package com.example.demo.diary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.diary.dto.Attachment;

@Mapper
public interface AttachmentDao {
  int insertAttachment(Attachment attachment);        // 첨부파일 저장
  List<Attachment> selectAttachmentsByDid(int did);   // 특정 일기 첨부 전체 조회
  Attachment selectFirstAttachmentByDid(int did);     // 대표사진(첫 번째 첨부) 조회
  int deleteAttachmentsByDid(int did);                // 특정 일기 첨부 전체 삭제
}
