// package com.example.demo.diary.service;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.example.demo.diary.dao.DiaryDao;
// import com.example.demo.diary.dto.Diary;
// import com.example.demo.diary.dto.Pager;
// import com.example.demo.diary.dto.request.DiaryRequest;
// import com.example.demo.diary.dto.response.DiaryResponse;
// import com.example.demo.diary.enums.Emo;
// import com.example.demo.diary.enums.Weather;

// import lombok.AllArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Service
// @AllArgsConstructor
// public class DiaryService {

//   private final DiaryDao diaryDao;

//   //@Autowired
//   //private AttachmentDao attachmentDao;

//   //@Autowired
//   //private AttachmentService attachmentService;

//   // 일기 생성
//   @Transactional
//   public DiaryResponse createDiary(DiaryRequest dto) {
//     // 1) Entity에 저장 Request DTO(String) -> Diary(String) 변환(유효성검증목적)
//     Diary diary = new Diary();
//     diary.setAccount(dto.getAccount());
//     diary.setTitle(dto.getTitle());
//     diary.setContent(dto.getContent());
//     // String -> Enum 변환 후 다시 String으로 Diary에 저장(DB 저장을 위해서)
//     diary.setEmo(dto.getEmo());
//     diary.setWeather(dto.getWeather());
    
//     // 2) DB insert
//     diaryDao.insert(diary); // MyBatis useGeneratedKeys로 diary.did 채워짐
    
//     // 3) Entity에 다시 값 넣기 (INSERT 후 DB에서 다시 조회 (CreatedAt, updatedAt 포함) )
//     Diary dbDiary = diaryDao.selectByDid(diary.getDid());
    
//     // 4) Diary -> DiaryResponse타입으로 수정
//     DiaryResponse response = new DiaryResponse();
//     response.setDid(dbDiary.getDid());
//     response.setAccount(dbDiary.getAccount());
//     response.setTitle(dbDiary.getTitle());
//     response.setContent(dbDiary.getContent());
//     response.setCreatedAt(dbDiary.getCreatedAt());
//     response.setEmo(Emo.valueOf(dbDiary.getEmo().toUpperCase()));
//     response.setWeather(Weather.valueOf(dbDiary.getWeather().toUpperCase()));
//     // 3) Diary + Attachment 응답
//     return response;
//   }

//   // 전체 글 개수 (Pager 계산용)
//   public int countDiaries() {
//     return diaryDao.countDiaries();
//   }

//   // 일기 페이징 리스트 조회
//   public List<DiaryResponse> getDiariesPage(Pager pager) {
//     // 1. DB에서 Entity에 저장된 페이징된 다이어리 목록 조회
//     List<Diary> diariesList = diaryDao.selectDiariesByPage(pager);
    
//     return diariesList.stream().map(diary-> {
//       DiaryResponse response = new DiaryResponse();
//       response.setDid(diary.getDid());
//       response.setAccount(diary.getAccount());
//       response.setTitle(diary.getTitle());
//       response.setContent(diary.getContent());
//       response.setCreatedAt(diary.getCreatedAt());
//       response.setUpdatedAt(diary.getUpdatedAt());
//       //String -> ENUM으로 변환
//       response.setEmo(Emo.valueOf(diary.getEmo().toUpperCase()));
//       response.setWeather(Weather.valueOf(diary.getWeather().toUpperCase()));
//       return response;
//     }).collect(Collectors.toList());

//     // 2. Diary -> DiaryReponse 변환 (대표 이미지 1장, 사진 목록)
//     // List<DiaryResponse> diaryResponses = diariesList.stream().map(d -> {
//     //   List<Attachment> attachments = attachmentDao.selectAttachmentsByDid(d.getDid());
//     //   List<AttachmentResponse> attachmentResponses = attachments.stream()
//     //       .map(a -> new AttachmentResponse(a.getAid(), a.getAname(), a.getAtype(), "/attachments/" + a.getAid()))
//     //       .toList();
//     //   Attachment rep = attachmentDao.selectThumbnailByDid(d.getDid());
//     //   AttachmentResponse thumbnail = new AttachmentResponse(rep.getAid(), rep.getAname(), rep.getAtype(),
//     //       "/attachments/" + rep.getAid());
//     //   // Diary(String) -> DiaryResponse(ENUM) 으로 변환
//     //   return new DiaryResponse(d.getDid(), d.getMid(), d.getTitle(), d.getContent(),
//     //       ViewScope.valueOf(d.getViewScope()), // String -> Enum
//     //       Emo.valueOf(d.getEmo()), // String -> Enum
//     //       Weather.valueOf(d.getWeather()), // String -> Enum
//     //       d.getCreatedAt(), d.getUpdatedAt(),
//     //       attachmentResponses, thumbnail // attachments, thumbnail
//     //   );
//     // }).toList();
//     // // 3. Map에 pager + diaries 넣기
//     // Map<String, Object> result = new HashMap<>();
//     // result.put("pager", pager);
//     // result.put("diaries", diaryResponses);

//     // return result;

//   }

//   // 일기 상세 읽기
//   public DiaryResponse getDiary(Long did) {
//     // 1. 일기 본문 조회
//     Diary dbDiary = diaryDao.selectByDid(did);
//     // 2. 첨부파일 목록 조회(일기 본문, 사진여러장, 썸네일은 보내주지 않고 URL로 보냄)
//     //List<Attachment> attachments = attachmentDao.selectAttachmentsByDid(did);
//     // 3. Diary -> Response 변환
//     // List<AttachmentResponse> attachmentResponses = attachments.stream()
//     //     .map(a -> new AttachmentResponse(a.getAid(), a.getAname(), a.getAtype(),
//     //         "/attachments/" + a.getAid() // 첨부파일 조회해서 응답 객체에 세팅(Service 안에서 첨부조회까지 처리), adata가 null로 응답해주어야 하고, 메타데이터만
//     //                                      // 내려주어야 한다.(실제 BLOB은 노출 안하게 하기 위함 + 성능향상)
//     //     )).toList();
//     // // 4. 대표이미지 (첫 번째 첨부파일) - 방법 1(원본 보내기)
//     // // Attachment thumbnail = attachmentDao.selectThumbnailByDid(did);
//     // // 4. 대표이미지 URL로 변환(썸네일을 첫 번째 첨부파일로 사용) - 방법2(URL로 보내기)
//     // AttachmentResponse thumbnailResponse = attachmentResponses.isEmpty() ? null : attachmentResponses.get(0);

//     // // 5. 컨트롤러로 response타입 객체에 값 담아서 반환 (DB String -> Response Enum 변환)
//     // DiaryResponse resp = new DiaryResponse();
//     // resp.setDid(diary.getDid());
//     // resp.setMid(diary.getMid());
//     // resp.setTitle(diary.getTitle());
//     // resp.setContent(diary.getContent());
//     // resp.setCreatedAt(diary.getCreatedAt());
//     // resp.setUpdatedAt(diary.getUpdatedAt());
//     // // ENUM
//     // resp.setViewScope(ViewScope.valueOf(diary.getViewScope()));
//     // resp.setEmo(Emo.valueOf(diary.getEmo()));
//     // resp.setWeather(Weather.valueOf(diary.getWeather()));
//     // // 사진(사진목록, 썸네일)
//     // resp.setAttachments(attachmentResponses);
//     // resp.setThumbnail(thumbnailResponse);
//     //return resp;

//     DiaryResponse response = new DiaryResponse();
//     response.setDid(dbDiary.getDid());
//     response.setAccount(dbDiary.getAccount());
//     response.setTitle(dbDiary.getTitle());
//     response.setContent(dbDiary.getContent());
//     response.setUpdatedAt(dbDiary.getUpdatedAt());
//     response.setCreatedAt(dbDiary.getCreatedAt());
//     response.setEmo(Emo.valueOf(dbDiary.getEmo().toUpperCase()));
//     response.setWeather(Weather.valueOf(dbDiary.getWeather().toUpperCase()));
//     return response;
//   }

//   // 수정
//   @Transactional
//   public void updateDiary(DiaryRequest dto) {
//     // 1. 일기 본문 업데이트
//     Diary updateDiary = new Diary();
//     // 1.1 변경할 콘텐츠 필드 설정
//     updateDiary.setTitle(dto.getTitle());
//     updateDiary.setContent(dto.getContent());
//     // 1.2 String -> Enum 변환(유효성 검증) 후 String으로 DB에 전달
//     updateDiary.setEmo(Emo.valueOf(dto.getEmo().toUpperCase()).name());
//     updateDiary.setWeather(Weather.valueOf(dto.getWeather().toUpperCase()).name());
//     // 2. 첨부파일 수정 및 삭제 로직 위임
//     //attachmentService.updateAttachments(dto);
//   }

//   // 단건 삭제
//   @Transactional
//   public void deleteDiary(Long did) {
//     // 1. 첨부파일 먼저 삭제 (종속성 관리)
//     // attachmentDao.delete(did);
//     // 2. 일기 본문 삭제
//     diaryDao.delete(did);
//   }

//   // 다중 삭제
//   @Transactional
//   public void deleteDiaries(List<Long> dids) {
//     // 1. 해당 일기들의 모든 첨부파일 먼저 삭제
//     // attachmentDao.deleteByDids(dids);
//     // 2. 일기 본문 다중 삭제
//     diaryDao.deleteDiaries(dids);
//   }
// }
