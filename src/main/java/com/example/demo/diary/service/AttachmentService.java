// package com.example.demo.diary.service;

// import java.io.BufferedInputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.multipart.MultipartFile;

// import com.example.demo.diary.dao.AttachmentDao;
// import com.example.demo.diary.dto.Attachment;
// import com.example.demo.diary.dto.request.AttachmentRequest;
// import com.example.demo.diary.dto.request.DiaryRequest;

// @Service
// @Transactional
// public class AttachmentService {

//   @Autowired
//   private AttachmentDao attachmentDao;

//   // 특정 일기에 새로운 파일을 추가합니다.
//   public void addAttachments(Long did, List<MultipartFile> files) {
//     if (files == null || files.isEmpty()) {
//       return;
//     }

//     if (files.size() > 10) {
//       throw new IllegalArgumentException("첨부파일은 최대 10개까지");
//     }

//     for (MultipartFile file : files) {
//       if (file != null && !file.isEmpty()) {
//         try (InputStream is = new BufferedInputStream(file.getInputStream())) {
//           byte[] data = is.readAllBytes();
//           Attachment attachment = new Attachment(
//               did,
//               file.getOriginalFilename(),
//               file.getContentType(),
//               data);
//           attachmentDao.insert(attachment);
//         } catch (IOException e) {
//           throw new RuntimeException("파일 업로드 처리 중 오류 발생: " + file.getOriginalFilename(), e);
//         }
//       }
//     }
//   }

//   // 특정 첨부파일 ID를 이용해 삭제합니다. (별도 DELETE API에서 사용 가능)
//   public void deleteAttachment(Long aid) {
//     attachmentDao.delete(aid);
//   }

//   // 첨부파일의 메타데이터(이름, 타입)를 수정하고, 삭제 요청을 처리합니다.
//   public void updateAttachments(DiaryRequest dto) {
//     Long did = dto.getDid();
//     Attachment thumbnail = attachmentDao.selectThumbnailByDid(did);
//     Long repAid = (thumbnail != null) ? thumbnail.getAid() : null;

//     // 1. 기존 파일 메타데이터 수정 처리
//     if (dto.getAttachments() != null) {
//       for (AttachmentRequest ar : dto.getAttachments()) {
//         if (ar.getAid() != null) {
//           Attachment updatedAttachment = new Attachment();
//           updatedAttachment.setAid(ar.getAid());

//           // AttachmentRequest DTO에 이름/타입 필드가 있다고 가정
//           updatedAttachment.setAname(ar.getAname());
//           updatedAttachment.setAtype(ar.getAtype());
//           updatedAttachment.setAdata(null); // 파일 데이터는 수정하지 않음

//           attachmentDao.update(updatedAttachment);
//         }
//       }
//     }

//     // 2. 삭제 처리
//     if (dto.getDeleteAids() != null) {
//       dto.getDeleteAids().stream()
//           .filter(aid -> repAid == null || !aid.equals(repAid))
//           .forEach(attachmentDao::delete);
//     }
//   }
// }

/*
 // 3) List 반환타입 선언
    List<AttachmentResponse> attachmentResponses = new ArrayList<>();
    // 3-1)첨부파일이 있으면 Attachment 저장
    if (files != null && !files.isEmpty()) {
      // 사진이 10장 이상이면 예외를 던진다.
      if (files.size() > 10) {
        throw new IllegalArgumentException("첨부파일은 최대 10개까지");
      }
      for (MultipartFile file : files) {
        try (InputStream is = new BufferedInputStream(file.getInputStream())) {
          byte[] data = is.readAllBytes(); // 버퍼로 읽기 때문에 성능 최적화
          // Attachment 객체 생성
          Attachment attachment = new Attachment();
          attachment.setDid(savedDiary.getDid());
          attachment.setAname(file.getOriginalFilename());
          attachment.setAtype(file.getContentType());
          attachment.setAdata(data);
          // Insert
          attachmentDao.insert(attachment);
          // MAX(AID) 조회 -PK세팅
          Long aid = attachment.getAid();
          // 클라이언트 전달용 DTO 생성 ,url은 /attachments/{aid} 이런 식으로 접근 가능하게 설계
          String url = "/attachments/" + aid;
          attachmentResponses.add(new AttachmentResponse(aid, attachment.getAname(), attachment.getAtype(), url));
        } catch (IOException e) {
          throw new RuntimeException("파일 업로드 처리 중 오류 발생: " + file.getOriginalFilename(), e);
        }
      }
    }
 // 4) Diary + Attachment 응답
    DiaryResponse resp = new DiaryResponse();
    resp.setDid(savedDiary.getDid());
    resp.setMid(savedDiary.getMid());
    resp.setTitle(savedDiary.getTitle());
    resp.setContent(savedDiary.getContent());
    resp.setCreatedAt(savedDiary.getCreatedAt());
    // 사진리스트(썸네일, 여러장의 사진) DiaryResponse에 담기
    resp.setAttachments(attachmentResponses);
    // Diary(String -> DiaryResponse(ENUM)으로 변환)
    resp.setViewScope(ViewScope.valueOf(savedDiary.getViewScope()));
    resp.setEmo(Emo.valueOf(savedDiary.getEmo()));
    resp.setWeather(Weather.valueOf(savedDiary.getWeather()));
 */