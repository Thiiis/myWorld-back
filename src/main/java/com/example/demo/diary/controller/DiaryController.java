package com.example.demo.diary.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.diary.dto.Diary;
import com.example.demo.diary.dto.DiaryCreateRequest;
import com.example.demo.diary.dto.DiaryResponse;
import com.example.demo.diary.dto.DiaryUpdateRequest;
import com.example.demo.diary.service.DiaryService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

  private final DiaryService diaryService;
  
   @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DiaryResponse> createDiary(
      @RequestPart("diary") DiaryCreateRequest request,
      @RequestPart(value = "files", required = false) List<MultipartFile> files) throws IOException {
        
    Diary created = diaryService.createDiary(request, files);
    return (created != null) ? ResponseEntity.ok(new DiaryResponse(created)) : ResponseEntity.badRequest().build();
  }

  @GetMapping("/detail")
  public ResponseEntity<DiaryResponse> getDiary(@RequestParam("did") Long did) {
    Diary diary = diaryService.getDiary(did);
    return (diary != null) ? ResponseEntity.ok(new DiaryResponse(diary)) : ResponseEntity.notFound().build();
  }

  // @GetMapping("/list/paged")
  // public ResponseEntity<Map<String, Object>> getDiariesPaged(
  //     @RequestParam(name="pageNo", defaultValue = "1") int pageNo,
  //     @RequestParam(name="rowsPerPage", defaultValue = "6") int rowsPerPage,
  //     @RequestParam(name="pagesPerGroup", defaultValue = "5") int pagesPerGroup) {
  //   return ResponseEntity.ok(diaryService.getDiariesPaged(pageNo, rowsPerPage, pagesPerGroup));
  // }

  // @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  // public ResponseEntity<DiaryResponse> updateDiary(
  //     @RequestPart("diary") DiaryUpdateRequest request,
  //     @RequestPart(value = "files", required = false) List<MultipartFile> files,
  //     @RequestPart(value = "deleteAids", required = false) List<Long> deleteAids) throws IOException {

  //   Diary updated = diaryService.updateDiaryWithFiles(request, files, deleteAids);
  //   return (updated != null) ? ResponseEntity.ok(new DiaryResponse(updated)) : ResponseEntity.notFound().build();
  // }
  
  // // 상세페이지 단건 삭제
  // @DeleteMapping("/delete/{did}")
  // public ResponseEntity<Void> deleteDiary(@PathVariable("did") Long did) {
  //   int deleted = diaryService.deleteDiary(did);
      
  //   return (deleted > 0) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  // }

  // // 페이징 목록에서 여러 개 선택 삭제
  // @DeleteMapping("/delete")
  // public ResponseEntity<Void> deleteDiaries(@RequestBody List<Long> didList) {
    
  //   int deletedCount = diaryService.deleteDiaries(didList);
  //   return (deletedCount > 0) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

  // }
}
