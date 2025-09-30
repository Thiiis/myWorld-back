// package com.example.demo.diary.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.example.demo.diary.dto.request.DiaryRequest;
// import com.example.demo.diary.dto.response.AttachmentResponse;
// import com.example.demo.diary.dto.response.DiaryResponse;
// import com.example.demo.diary.service.AttachmentService;

// @RestController
// @RequestMapping("/diaries/{did}/attache")
// public class DiaryAttachmentController {

//   @Autowired
//   AttachmentService attachmentService;

//   //사진 생성
//   @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//   public AttachmentResponse createAttachment(@PathVariable("did") Long did, @RequestPart(value = "files", required = false) List<MultipartFile> files) {
//     AttachmentResponse response = attachmentService.createAttachment(did, files);
//     return response;
//   }

//   //사진 읽기

//   //사진 리스트로 읽기

//   //사진 수정
//   @PutMapping(value = "/update/{did}", consumes = "multipart/form-data")
//   public ResponseEntity<Void> updateDiary(@PathVariable("did") Long did, @RequestPart("dto") DiaryRequest dto,
//                                           @RequestPart(value = "files", required = false) List<MultipartFile> files) {

//     dto.updateRequest(did);
//     diaryService.updateDiary(dto, files);

//     return ResponseEntity.noContent().build();
//   }

//   //사진 삭제

//   //사진다중 삭제
  
// }
