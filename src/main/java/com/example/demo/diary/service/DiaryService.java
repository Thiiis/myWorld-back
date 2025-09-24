package com.example.demo.diary.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.diary.dao.AttachmentDao;
import com.example.demo.diary.dao.DiaryDao;
import com.example.demo.diary.dto.Attachment;
import com.example.demo.diary.dto.Diary;
import com.example.demo.diary.dto.Pager;
import com.example.demo.diary.dto.request.DiaryCreateRequest;
import com.example.demo.diary.dto.request.DiaryUpdateRequest;
import com.example.demo.diary.dto.response.DiaryCreateResponse;
import com.example.demo.diary.dto.response.DiaryPageResponse;
import com.example.demo.diary.dto.response.DiaryReadResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService {

  private final DiaryDao diaryDao;

  // 일기 생성
  public DiaryCreateResponse createDiary(DiaryCreateRequest request) {
    Diary diary = request.updateStartData();

    diaryDao.insertDiary(diary);
    return new DiaryCreateResponse(diary.getDid(),
        diary.getMid(),
        diary.getTitle(),
        diary.getContent(),
        diary.getViewScope(),
        diary.getEmo(),
        diary.getWeather(),
        diary.getCreatedAt());
  }

  // 일기 상세 읽기
  public DiaryReadResponse getDiary(Long did) {
    Diary diary = diaryDao.selectDiaryById(did);
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

  // public void updateDiary(DiaryUpdateRequest request) {
  //   diaryDao.updateDiary(request);
  // }

}
