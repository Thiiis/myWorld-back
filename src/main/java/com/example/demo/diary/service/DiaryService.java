package com.example.demo.diary.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.diary.dao.AttachmentDao;
import com.example.demo.diary.dao.DiaryDao;
import com.example.demo.diary.dto.Attachment;
import com.example.demo.diary.dto.Diary;
import com.example.demo.diary.dto.Pager;
import com.example.demo.diary.dto.request.DiaryCreateRequest;
import com.example.demo.diary.dto.request.DiaryUpdateRequest;
import com.example.demo.diary.dto.response.AttachmentCreateResponse;
import com.example.demo.diary.dto.response.DiaryCreateResponse;
import com.example.demo.diary.dto.response.DiaryPageResponse;
import com.example.demo.diary.dto.response.DiaryReadResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService {

  private final DiaryDao diaryDao;
  private final AttachmentDao attachmentDao;

  // 일기 생성
  public DiaryCreateResponse createDiary(DiaryCreateRequest request, List<MultipartFile> files) {
    // 1) Diary 저장
    Diary diary = request.updateStartData();
    diaryDao.insertDiary(diary);

    //INSERT 후 다시 조회해서 createdAt 채움
    Diary savedDiary = diaryDao.selectDiaryById(diary.getDid());

    // List 반환타입 선언
    List<AttachmentCreateResponse> attachmentCreateResponses = new ArrayList<>();

    // 2) 첨부파일이 있으면 Attachment 저장
    if(files != null && !files.isEmpty()) {
      
      if (files.size() > 10) {
        throw new IllegalArgumentException("첨부파일은 최대 10개까지");
      }

      for (MultipartFile file : files) {
        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
             ByteArrayOutputStream bos = new ByteArrayOutputStream();)
             {
              byte[] buffer = new byte[8192];  //8KB 버퍼
              int bytesRead;

              while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
              }

              byte[] fileData = bos.toByteArray();

              // Attachment 객체 생성
              Attachment attachment = new Attachment();
              attachment.updateDid(diary.getDid());
              attachment.updateAname(file.getOriginalFilename());
              attachment.updateAtype(file.getContentType());
              attachment.updateAdata(fileData);

              //Insert
              attachmentDao.insertAttachment(attachment);

              //MAX(AID) 조회 -PK세팅
              Long aid = attachmentDao.selectInsertedAttachmentId(attachment);
              attachment.updateAid(aid);

              // 클라이언트 전달용 DTO 생성 ,url은 /attachments/{aid} 이런 식으로 접근 가능하게 설계
              String url = "/attachments/" + aid;
              attachmentCreateResponses.add(new AttachmentCreateResponse(aid, attachment.getAname(), attachment.getAtype(), url));
              
             } catch(IOException e) {
              throw new RuntimeException("파일 업로드 처리 중 오류 발생: " + file.getOriginalFilename(), e);
             }
         }
    }

    // 3) Diary + Attachment 응답
    return new DiaryCreateResponse(savedDiary.getDid(), savedDiary.getMid(), savedDiary.getTitle(), savedDiary.getContent(), savedDiary.getViewScope(), savedDiary.getEmo(), savedDiary.getWeather(), savedDiary.getCreatedAt(), attachmentCreateResponses);
  }

  // 일기 상세 읽기
  public DiaryReadResponse getDiary(Long did) {
    Diary diary = diaryDao.selectDiaryById(did);

    // 첨부파일 조회해서 응답 객체에 세팅(Service 안에서 첨부조회까지 처리), adata가 null로 응답해주어야 하고, 메타데이터만 내려주어야 한다.(실제 BLOB은 노출 안하게 하기 위함 + 성능향상)
    // 첨부파일 메타데이터 조회
    //List<Attachment> attachments = attachmentDao.selectAttachmentsByDid(did);
    //diary.updateAttachments(attachmentDao.insertAttachment(attachments);

    // 대표이미지
    //Attachment representative = attachmentDao.selectFirstAttachmentByDid(did);
    //diary.updateRepresentativeImage(attachmentDao.selectFirstAttachmentByDid(representative);

    return new DiaryReadResponse(diary.getDid(), diary.getMid(), diary.getTitle(), diary.getContent(), diary.getViewScope(), diary.getEmo(), diary.getWeather(), diary.getCreatedAt(), diary.getUpdatedAt());
  }
  
  // 전체 글 개수 (Pager 계산용)
  public int countDiaries() {
    return diaryDao.countDiaries();
  }

  // 일기 페이징 리스트 조회
  public DiaryPageResponse getDiariesPage(Pager pager) {
    //1. DAO로 데이터 전달
    List<Diary> diaries = diaryDao.selectDiariesByPage(pager);
    //2. 리턴하기 위한 타입 변환(Diary -> DiaryReadResponse)
    List<DiaryReadResponse> list = diaries.stream().map(d -> new DiaryReadResponse( d.getDid(), d.getMid(), d.getTitle(), d.getContent(), d.getViewScope(), d.getEmo(), d.getWeather(), d.getCreatedAt(), d.getUpdatedAt())).toList();
    return new DiaryPageResponse(pager, list);
  }

  //수정
  public void updateDiary(DiaryUpdateRequest request) {
    diaryDao.updateDiary(request);
  }

  //삭제
  public void deleteDiary(Long did) {
    diaryDao.deleteDiary(did);
  }

}
