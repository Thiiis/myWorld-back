package com.example.demo.diary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.diary.dto.Attachments;
import com.example.demo.diary.dto.response.AttachmentsResponse;
import com.example.demo.diary.service.AttachmentsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/diaries/{did}/attache")
@RequiredArgsConstructor
public class DiarysAttachmentController {

  private final AttachmentsService attachmentService;

  // // 사진 생성
  // @PostMapping(value = "/create", consumes =
  // MediaType.MULTIPART_FORM_DATA_VALUE)
  // public ResponseEntity<List<AttachmentsResponse>>
  // createAttac(@PathVariable("did") Long did, @RequestPart(value = "files",
  // required = false) List<MultipartFile> files) {
  // List<AttachmentsResponse> response = attachmentService.createAttach(did,
  // files);
  // return ResponseEntity.status(HttpStatus.CREATED).body(response);
  // }

  // // 사진 상세읽기
  // @GetMapping("/list/{aid}")
  // public ResponseEntity<AttachmentsResponse> getAttache(@PathVariable("did")
  // Long did, @PathVariable("aid") Long aid) {
  // AttachmentsResponse response = attachmentService.getAttache(did, aid);
  // return ResponseEntity.ok(response);
  // }

  // //사진 리스트로 읽기
  // @GetMapping("/list")
  // public ResponseEntity<List<AttachmentsResponse>>
  // getAttachements(@PathVariable("did") Long did) {
  // List<AttachmentsResponse> attachments =
  // attachmentService.getAttachmentsByDiary(did);
  // return ResponseEntity.ok(attachments);
  // }

  // //사진 수정
  // @PostMapping(value = "/update", consumes = "multipart/form-data")
  // public ResponseEntity<Void> updateDiary(@PathVariable("did") Long did,
  // @RequestParam("aids") List<Long> deleteAids, @RequestPart(value = "files",
  // required = false) List<MultipartFile> addfiles) {

  // attachmentService.updateAttach(did, deleteAids, addfiles);

  // return ResponseEntity.noContent().build();
  // }

  // // 첨부파일 삭제
  // @DeleteMapping("/delete/{aid}")
  // public ResponseEntity<Void> deleteAttach(@PathVariable("did") Long did,
  // @PathVariable("aid") Long aid) {
  // attachmentService.deleteAttach(did,aid);
  // return ResponseEntity.noContent().build();
  // }

  // 이미지 실제 데이터 반환 (브라우저 <img src="..."> 용)
  // JSON 메타데이터 조회
  // @GetMapping("/list/{aid}")
  // public ResponseEntity<AttachmentsResponse> getAttache(
  //     @PathVariable("did") Long did,
  //     @PathVariable("aid") Long aid) {
  //   AttachmentsResponse response = attachmentService.getAttache(did, aid);
  //   return ResponseEntity.ok(response);
  // }

  // 실제 파일 응답 (브라우저 <img> 용)
  @GetMapping("/file/{aid}")
  public ResponseEntity<byte[]> getAttachFile(
      @PathVariable("did") Long did,
      @PathVariable("aid") Long aid) {
    Attachments att = attachmentService.getAttachmentEntity(aid);
    return ResponseEntity.ok()
        .contentType(MediaType.valueOf(att.getAtype())) // image/png
        .body(att.getAdata()); // 실제 byte[]
  }

}
