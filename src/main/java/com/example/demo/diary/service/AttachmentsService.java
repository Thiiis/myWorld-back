package com.example.demo.diary.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.diary.dao.AttachmentsDao;
import com.example.demo.diary.dto.Attachments;
import com.example.demo.diary.dto.response.AttachmentsResponse;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AttachmentsService {

  private final AttachmentsDao attachmentDao;

  // 사진 생성(리스트)
  public List<AttachmentsResponse> createAttach(Long did, List<MultipartFile> files) {
    // 1. 사진 담을 객체 생성
    List<AttachmentsResponse> responses = new ArrayList<>();
    // 2. 개별 사진 for문으로 List로 값 담기
    for (MultipartFile file : files) {
      try {
      // 2-1. Entity 저장
      Attachments attachment = new Attachments();
      attachment.setDid(did);
      attachment.setAname(file.getOriginalFilename());
      attachment.setAtype(file.getContentType());
      attachment.setAdata(file.getBytes());
      // 2-2. DB 저장
      attachmentDao.insert(attachment);
      // 2-3. 리턴타입으로 반환
      AttachmentsResponse response = new AttachmentsResponse();
      response.setAid(attachment.getAid());
      response.setAname(attachment.getAname());
      response.setAtype(attachment.getAtype());
      response.setUrl("/diaries/" + did + "/attache/list/" + attachment.getAid()); // 클라이언트 접근용(데이터 대신)
      // 2-4. 객체 List에 담기
      responses.add(response);
      } catch(IOException e) {
        throw new RuntimeException("파일 저장 실패", e);
      }
    }
      return responses;
  }

  // 상세 조회
  public AttachmentsResponse getAttache(Long did, Long aid) {
    // 1. aid 기준으로 사진 조회
    Attachments att = attachmentDao.selectAttachByAid(aid);
    // 2. attachments -> AttachmentsResponse 변환
    AttachmentsResponse response = new AttachmentsResponse();
    response.setAid(att.getAid());
    response.setAname(att.getAname());
    response.setAtype(att.getAtype());
    response.setUrl("/diaries/" + did + "/attache/list/" + att.getAid());

    return response;
  }

  // 사진 리스트로 조회
  public List<AttachmentsResponse> getAttachmentsByDiary(Long did) {
    return attachmentDao.selectAttachByDid(did).stream().map(a -> new AttachmentsResponse(a.getAid(), a.getAname(), a.getAtype(), "/diaries/" + did + "/attache/list/" + a.getAid())).collect(Collectors.toList());
  }

  // 특정 일기의 대표사진(첫 번째) 조회
  public AttachmentsResponse getThumbnailByDiary(Long did) {
    List<AttachmentsResponse> attachments = getAttachmentsByDiary(did);
    return attachments.get(0);
  }

  // 사진 리스트 수정
  public void updateAttach(Long did, List<Long> deleteAids, List<MultipartFile> addfiles) {
    
    // 1. DB에서 삭제
    if(deleteAids != null && !deleteAids.isEmpty()) {
      for(long aid: deleteAids) {
        attachmentDao.delete(aid);  
      }
    }

    // 2. DB에 추가
    if(addfiles != null && !addfiles.isEmpty()) {
      for(MultipartFile file : addfiles) {
        Attachments attachment = new Attachments();
        attachment.setDid(did);
        attachment.setAname(file.getOriginalFilename());
        attachment.setAtype(file.getContentType());
        try {
          attachment.setAdata(file.getBytes()); //파일 내용을 BLOB으로 저장
        } catch(IOException e) {
          //예외처리
        }
        attachmentDao.insert(attachment); //DB에 추가
      }
    }
  }

  // 첨부파일 단일 삭제
  public void deleteAttach(Long did, Long aid) {
    attachmentDao.delete(aid);
  }

  // 단일일기 전체첨부파일 삭제
  public void deleteAttachByDiary(Long did) {
    attachmentDao.deleteByDid(did);
  }

  // 다중일기 첨부파일 삭제
  public void deleteAttachByDiaries(List<Long> dids) {
    attachmentDao.deleteByDids(dids);
  }
  
}