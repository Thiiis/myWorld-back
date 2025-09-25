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
import com.example.demo.diary.dto.request.AttachmentUpdateRequest;
import com.example.demo.diary.dto.request.DiaryCreateRequest;
import com.example.demo.diary.dto.request.DiaryUpdateRequest;
import com.example.demo.diary.dto.response.AttachmentCreateResponse;
import com.example.demo.diary.dto.response.DiaryCreateResponse;
import com.example.demo.diary.dto.response.DiaryPageResponse;
import com.example.demo.diary.dto.response.DiaryReadResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryService {

  private final DiaryDao diaryDao;
  private final AttachmentDao attachmentDao;

  // 일기 생성
  public DiaryCreateResponse createDiary(DiaryCreateRequest dto, List<MultipartFile> files) {
    // 1) Diary 저장
    Diary diary = new Diary(dto.getMid(), dto.getTitle(), dto.getContent(), dto.getViewScope(), dto.getEmo(), dto.getWeather());
    // 1-1) DB insert
    diaryDao.insert(diary);
    // 1-2) INSERT 후 Did,createdAt를 Select해서 클라이언트에게 전송할 값 Diary객체에 담기
    Diary savedDiary = diaryDao.selectByDid(diary.getDid());
    // 2) List 반환타입 선언
    List<AttachmentCreateResponse> attachmentCreateResponses = new ArrayList<>();
    // 2-1)첨부파일이 있으면 Attachment 저장
    if (files != null && !files.isEmpty()) {

      if (files.size() > 10) {
        throw new IllegalArgumentException("첨부파일은 최대 10개까지");
      }

      for (MultipartFile file : files) {
        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
          byte[] buffer = new byte[8192]; // 8KB 버퍼
          int bytesRead;

          while ((bytesRead = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
          }

          byte[] fileData = bos.toByteArray();

          // Attachment 객체 생성
          Attachment attachment = new Attachment(diary.getDid(), file.getOriginalFilename(), file.getContentType(),
              fileData);

          // Insert
          attachmentDao.insert(attachment);

          // MAX(AID) 조회 -PK세팅
          Long aid = attachmentDao.selectInsertedAttachmentId(attachment);

          // 클라이언트 전달용 DTO 생성 ,url은 /attachments/{aid} 이런 식으로 접근 가능하게 설계
          String url = "/attachments/" + aid;
          attachmentCreateResponses
              .add(new AttachmentCreateResponse(aid, attachment.getAname(), attachment.getAtype(), url));

        } catch (IOException e) {
          throw new RuntimeException("파일 업로드 처리 중 오류 발생: " + file.getOriginalFilename(), e);
        }
      }
    }
    // 3) Diary + Attachment 응답
    return new DiaryCreateResponse(savedDiary.getDid(), savedDiary.getMid(), savedDiary.getTitle(),
        savedDiary.getContent(), savedDiary.getViewScope(), savedDiary.getEmo(), savedDiary.getWeather(),
        savedDiary.getCreatedAt(), attachmentCreateResponses);
  }

  // 전체 글 개수 (Pager 계산용)
  public int countDiaries() {
    return diaryDao.countDiaries();
  }

  // 일기 페이징 리스트 조회
  public DiaryPageResponse getDiariesPage(Pager pager) {

    // 1. 페이징된 다이어리 목록 조회 / DAO로 데이터 전달
    List<Diary> diaries = diaryDao.selectDiariesByPage(pager);

    // 2. Response 변환(대표 이미지 1장만) / 리턴하기 위한 타입 변환(Diary -> DiaryReadResponse)
    List<DiaryReadResponse> list = diaries.stream().map(d -> {
      Attachment representative = attachmentDao.selectFeaturedByDid(d.getDid());

      AttachmentCreateResponse repResponse = representative != null
          ? new AttachmentCreateResponse(representative.getAid(), representative.getAname(), representative.getAtype(),
              "/attachments/" + representative.getAid())
          : null;

      return new DiaryReadResponse(d.getDid(), d.getMid(), d.getTitle(), d.getContent(), d.getViewScope(), d.getEmo(),
          d.getWeather(), d.getCreatedAt(), d.getUpdatedAt(), null, repResponse);

    }).toList();

    return new DiaryPageResponse(pager, list);
  }

  // 일기 상세 읽기
  public DiaryReadResponse getDiary(Long did) {

    // 1. 일기 본문 조회
    Diary diary = diaryDao.selectByDid(did);

    // 첨부파일 조회해서 응답 객체에 세팅(Service 안에서 첨부조회까지 처리), adata가 null로 응답해주어야 하고, 메타데이터만
    // 내려주어야 한다.(실제 BLOB은 노출 안하게 하기 위함 + 성능향상)
    // 2. 첨부파일 메타데이터 조회
    List<Attachment> attachments = attachmentDao.selectAttachmentsByDid(did);

    // 3. 대표이미지 (첫 번째 첨부파일)
    Attachment representative = attachmentDao.selectFeaturedByDid(did);

    // 4. Diary -> Response 변환
    List<AttachmentCreateResponse> attachmentResponses = attachments.stream().map(a -> new AttachmentCreateResponse(
        a.getAid(), a.getAname(), a.getAtype(), "/attachments/" + a.getAid())).toList();

    return new DiaryReadResponse(diary.getDid(), diary.getMid(), diary.getTitle(), diary.getContent(),
        diary.getViewScope(), diary.getEmo(), diary.getWeather(), diary.getCreatedAt(), diary.getUpdatedAt(),
        attachmentResponses,
        representative != null
            ? new AttachmentCreateResponse(representative.getAid(), representative.getAname(),
                representative.getAtype(), "/attachments/" + representative.getAid())
            : null);
  }

  // 수정
  public void updateDiary(DiaryUpdateRequest dto, List<MultipartFile> files) {
    // 0) MID 값 조회
    Diary existingDiary = diaryDao.selectByDid(dto.getDid());
    Long mid = existingDiary.getMid();

    // 1) Diary에 클라이언트로부터 요청받은 수정 값 Diary 객체에 담기
    Diary diary = new Diary(dto.getDid(), mid, dto.getTitle(), dto.getContent(), dto.getViewScope(), dto.getEmo(), dto.getWeather());
    log.info("Updating diary with ID: {}", dto.getDid()); // 여기에서 did 값이 제대로 들어가는지 로그로 확인할 수 있습니다.
    // 1-1)값을 담은 객체를 DB에 넣기
    diaryDao.update(diary);
    // 2) 삭제할 첨부파일 처리 (대표사진 제외)
    Long repAid = attachmentDao.selectFeaturedByDid(dto.getDid()) != null
        ? attachmentDao.selectFeaturedByDid(dto.getDid()).getAid()
        : null;

    if (dto.getDeleteAids() != null && !dto.getDeleteAids().isEmpty()) {
      for (Long aid : dto.getDeleteAids()) {
        if (repAid == null || !aid.equals(repAid)) {
          attachmentDao.delete(aid);  // AID로 파일 삭제
        }
      }
    }

    // 3) 새 파일 추가
    if (files != null && !files.isEmpty()) {
      for (MultipartFile file : files) {
        try {
           log.info("Inserting attachment with DID: {}", diary.getDid()); // 여기서 DID 값 확인
          //새로운 파일 추가 시, DID를 기준으로 삽입
          Attachment attachment = new Attachment(diary.getDid(), file.getOriginalFilename(), file.getContentType(), file.getBytes());
          attachmentDao.insert(attachment);
          } catch(IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
          }
        }
      }
    }

  // 삭제
  public void deleteDiary(Long did) {
    diaryDao.delete(did);
  }

}
