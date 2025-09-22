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
import com.example.demo.diary.dto.Pager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService {

  private final DiaryDao diaryDao;
  private final AttachmentDao attachmentDao;

  // 일기 생성
  @Transactional(rollbackFor = Exception.class)
  public Diary createDiary(Diary diary, List<MultipartFile> files) throws IOException {

    diary.setCreatedAt(OffsetDateTime.now());
    diary.setUpdatedAt(OffsetDateTime.now());
    diaryDao.insertDiary(diary); // DB INSERT

    Diary created = diaryDao.selectLatestDiary(diary.getMid());

    // 첨부파일이 있으면 처리
    if (files != null && !files.isEmpty()) {
      if (files.size() > 10) {
        throw new IllegalArgumentException("첨부파일은 최대 10개까지 업로드 가능합니다.");
      }
      for (MultipartFile file : files) {
        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream()) 
          {
          byte[] buffer = new byte[8192]; // 8KB 버퍼
          int bytesRead;
          while ((bytesRead = bis.read(buffer)) != -1) {bos.write(buffer, 0, bytesRead);}

          Attachment attach = new Attachment();
          attach.setDid(created.getDid());
          attach.setAname(file.getOriginalFilename());
          //attach.setAdata(file.getBytes());
          attach.setAdata(bos.toByteArray()); // 보조스트림으로 읽어온 데이터
          attach.setAtype(file.getContentType());

          attachmentDao.insertAttachment(attach);
        }
      }
    }
    // 첨부파일 조회해서 응답 객체에 세팅(Service 안에서 첨부 조회까지 처리)//adata가 null인것이 맞다.메타데이터만 내려주고 실제
    // BLOB은 노출 안하려고 하기 떄문
    created.setAttachments(attachmentDao.selectAttachmentsByDid(created.getDid())); // 전체사진
    created.setRepresentativeImage(attachmentDao.selectFirstAttachmentByDid(created.getDid())); // 대표사진

    return created; // 방금 삽입된 행 재조회(DB Identity로 자동 생성으로 did 값 가져오기)
  }

  // 상세일기 읽기
  @Transactional
  public Diary getDiary(int did) {
    Diary diary = diaryDao.selectDiaryById(did);
    if (diary != null) {
      List<Attachment> atts = attachmentDao.selectAttachmentsByDid(did); // 첨부파일 목록 조회
      if (atts != null && !atts.isEmpty()) {
        diary.setAttachments(atts); // 첨부파일 목록 조회
        diary.setRepresentativeImage(atts.get(0)); // 대표사진 조회(첫 번째 첨부)
      } else {
        diary.setAttachments(null); // 첨부파일 없으면 null
        diary.setRepresentativeImage(null); // 첨부파일 없으면 null
      }
    }
    return diary;
  }

  // 일기 리스트 읽기
  public List<Diary> getAllDiaries() {
    return diaryDao.selectAllDiaries();
  }

  // 페이징 처리된 다이어리 목록
  @Transactional
  public Map<String, Object> getDiariesPaged(int pageNo, int rowsPerPage, int pagesPerGroup) {
    int totalRows = diaryDao.countDiaries();
    Pager pager = new Pager(rowsPerPage, pagesPerGroup, totalRows, pageNo);

    Map<String, Object> map = new HashMap<>();
    map.put("startRowNo", pager.getStartRowNo());
    map.put("endRowNo", pager.getEndRowNo());

    List<Diary> diaries = diaryDao.selectDiariesByPage(map);

    // 대표사진 붙여주기
    for (Diary diary : diaries) {
      List<Attachment> atts = attachmentDao.selectAttachmentsByDid(diary.getDid());
      if (atts != null && !atts.isEmpty()) {
        diary.setAttachments(atts);
        diary.setRepresentativeImage(atts.get(0)); // Diary DTO에 필드 추가
      } else {
        diary.setAttachments(null);
        diary.setRepresentativeImage(null);
      }
    }

    Map<String, Object> result = new HashMap<>();
    result.put("pager", pager);
    result.put("diaries", diaries);
    return result;
  }

  // 일기 수정
  @Transactional
  public int updateDiaryWithFiles(Diary diary, List<MultipartFile> newFiles, List<Integer> deleteAids)
      throws IOException {
    diary.setUpdatedAt(OffsetDateTime.now());
    return diaryDao.updateDiary(diary);
  }

  // 단건일기 삭제(상세페이지용)
  @Transactional
  public int deleteDiary(int did) {
    attachmentDao.deleteAttachmentsByDid(did); // 첨부 먼저 삭제
    return diaryDao.deleteDiary(did);
  }

  // 다건일기 삭제(페이징리스트용)
  @Transactional
  public int deleteDiaries(List<Integer> didList) {
    int count = 0;

    return count;
  }

}
