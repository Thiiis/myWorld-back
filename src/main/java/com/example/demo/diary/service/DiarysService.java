package com.example.demo.diary.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.diary.dao.DiarysDao;
import com.example.demo.diary.dto.Diarys;
import com.example.demo.diary.dto.Pager;
import com.example.demo.diary.dto.request.DiarysRequest;
import com.example.demo.diary.dto.response.AttachmentsResponse;
import com.example.demo.diary.dto.response.DiarysResponse;
import com.example.demo.diary.enums.Emo;
import com.example.demo.diary.enums.Weather;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class DiarysService {

  private final DiarysDao diaryDao;
  private final AttachmentsService attachmentsService;

  // 일기 생성
  @Transactional
  public DiarysResponse createDiary(Long mid, DiarysRequest dto, List<MultipartFile> files) {
    // 1) Entity에 저장 Request DTO(String) -> Diary(String) 변환(유효성검증목적)
    Diarys diary = new Diarys();
    diary.setMid(mid);
    diary.setTitle(dto.getTitle());
    diary.setContent(dto.getContent());
    // String -> Enum 변환 후 다시 String으로 Diary에 저장(DB 저장을 위해서)
    diary.setEmo(dto.getEmo());
    diary.setWeather(dto.getWeather());
    // 2) DB insert
    diaryDao.insert(diary); // MyBatis useGeneratedKeys로 diary.did 채워짐
    // 3) Entity에 다시 값 넣기 (INSERT 후 DB에서 다시 조회 (CreatedAt, updatedAt 포함) )
    Diarys dbDiary = diaryDao.selectByDid(diary.getDid());
    // 4) 사진 저장 (AttachmentsService 호출)
    List<AttachmentsResponse> attachments = attachmentsService.createAttach(diary.getDid(), files);
    // 5) Diary -> DiaryResponse타입으로 수정
    DiarysResponse response = new DiarysResponse();
    response.setDid(dbDiary.getDid());
    response.setMid(dbDiary.getMid());
    response.setTitle(dbDiary.getTitle());
    response.setContent(dbDiary.getContent());
    response.setCreatedAt(dbDiary.getCreatedAt());
    response.setEmo(Emo.valueOf(dbDiary.getEmo().toUpperCase()));
    response.setWeather(Weather.valueOf(dbDiary.getWeather().toUpperCase()));
    // 6) Diary + Attachment 응답
    response.setAttachments(attachments); // 사진 리스트 포함
    return response;
  }

  // 전체 글 개수 (Pager 계산용)
  public int countDiaries(Long mid) {
    return diaryDao.countDiariesByMid(mid);
  }

  // 일기 페이징 리스트 조회
  public List<DiarysResponse> getDiariesPage(Long mid, Pager pager) {
    // 1. DB에서 Entity에 저장된 페이징된 다이어리 목록 조회
    List<Diarys> diariesList = diaryDao.selectDiariesByPage(mid, pager);
    
    return diariesList.stream().map(diary-> {
      DiarysResponse response = new DiarysResponse();
      response.setDid(diary.getDid());
      response.setMid(diary.getMid());
      response.setTitle(diary.getTitle());
      response.setContent(diary.getContent());
      response.setCreatedAt(diary.getCreatedAt());
      response.setUpdatedAt(diary.getUpdatedAt());
      // 2. String -> ENUM으로 변환
      response.setEmo(Emo.valueOf(diary.getEmo().toUpperCase()));
      response.setWeather(Weather.valueOf(diary.getWeather().toUpperCase()));
      // 3. 대표사진만
      response.setThumbnail(attachmentsService.getThumbnailByDiary(diary.getDid()));
      return response;
    }).collect(Collectors.toList());
  }
  
  // 일기 상세 읽기
  public DiarysResponse getDiary(Long mid ,Long did) {
    // 1. 일기 본문 조회
    Diarys dbDiary = diaryDao.selectByDid(did);    
    
    //2. DiaryResponse 타입으로 변환
    DiarysResponse response = new DiarysResponse();
    response.setDid(dbDiary.getDid());
    response.setMid(dbDiary.getMid());
    response.setTitle(dbDiary.getTitle());
    response.setContent(dbDiary.getContent());
    response.setUpdatedAt(dbDiary.getUpdatedAt());
    response.setCreatedAt(dbDiary.getCreatedAt());
    response.setEmo(Emo.valueOf(dbDiary.getEmo().toUpperCase()));
    response.setWeather(Weather.valueOf(dbDiary.getWeather().toUpperCase()));
    //3. 첨부파일 목록과 대표사진 가져온 후 DiaryResponse 타입으로 변환(객체에 값 넣기)
    List<AttachmentsResponse> attachments = attachmentsService.getAttachmentsByDiary(did);
    response.setAttachments(attachments);
    AttachmentsResponse thumbnail = attachments.get(0);
    response.setThumbnail(thumbnail);
    return response;
  }

  // 수정
  @Transactional
  public void updateDiary(Long mid, DiarysRequest dto, List<Long> deleteAids, List<MultipartFile> addFiles) {
    
    // 1. DB에서 기존 일기 조회
    Diarys existingDiary = diaryDao.selectByDid(dto.getDid());
    
    if (existingDiary.getMid() == null || !existingDiary.getMid().equals(mid)) {
        throw new RuntimeException("작성자가 아니어서 수정할 수 없습니다.");
    }
    // 1. 일기 본문 업데이트
    Diarys updateDiary = new Diarys();
    // 1.1 변경할 콘텐츠 필드 설정
    updateDiary.setDid(dto.getDid());
    updateDiary.setMid(mid);
    updateDiary.setTitle(dto.getTitle());
    updateDiary.setContent(dto.getContent());
    // 1.2 String -> Enum 변환(유효성 검증) 후 String으로 DB에 전달
    updateDiary.setEmo(Emo.valueOf(dto.getEmo().toUpperCase()).name());
    updateDiary.setWeather(Weather.valueOf(dto.getWeather().toUpperCase()).name());
    // 2. mapper 호출
    diaryDao.update(updateDiary);
    // 3. 첨부파일 수정 및 삭제 로직 위임
    attachmentsService.updateAttach(dto.getDid(), deleteAids, addFiles);
  }

  // 단건 삭제
  @Transactional
  public void deleteDiary(Long did, Long mid) {
    Diarys diary = diaryDao.selectByDid(did);
    if (!diary.getMid().equals(mid)) { 
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 글만 삭제할 수 있습니다.");
        }
    // 1. 첨부파일 먼저 삭제 (종속성 관리)
    attachmentsService.deleteAttachByDiary(did);
    // 2. 일기 본문 삭제
    diaryDao.delete(did);
  }

  // 다중 삭제
  @Transactional
  public void deleteDiaries(List<Long> dids) {
    // 1. 해당 일기들의 모든 첨부파일 먼저 삭제
     attachmentsService.deleteAttachByDiaries(dids);
    // 2. 일기 본문 다중 삭제
    diaryDao.deleteDiaries(dids);
  }
}
