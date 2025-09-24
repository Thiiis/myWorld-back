package com.example.demo.diary.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.diary.dao.AttachmentDao;
import com.example.demo.diary.dao.DiaryDao;
import com.example.demo.diary.dto.Attachment;
import com.example.demo.diary.dto.Diary;
import com.example.demo.diary.dto.DiaryCreateRequest;
import com.example.demo.diary.dto.DiaryResponse;
import com.example.demo.diary.dto.DiaryUpdateRequest;
import com.example.demo.diary.dto.Pager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService {

  private final DiaryDao diaryDao;

  // 일기 생성
  public DiaryResponse createDiary(DiaryCreateRequest request) {
    Diary diary = new Diary(request.getMid(), request.getTitle(), request.getContent(),
        request.getViewScope(), request.getEmo(), request.getWeather());

    diaryDao.insertDiary(diary);
    return new DiaryResponse(diary);
  }

  // 일기 상세읽기
  public DiaryResponse getDiary(Long did) {
    Diary diary = diaryDao.selectDiaryById(did);

    return new DiaryResponse(diary);
  }

}
