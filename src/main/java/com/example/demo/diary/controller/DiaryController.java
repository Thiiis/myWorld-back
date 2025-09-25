package com.example.demo.diary.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.diary.dto.Pager;
import com.example.demo.diary.dto.request.DiaryCreateRequest;
import com.example.demo.diary.dto.request.DiaryUpdateRequest;
import com.example.demo.diary.dto.response.DiaryCreateResponse;
import com.example.demo.diary.dto.response.DiaryPageResponse;
import com.example.demo.diary.dto.response.DiaryReadResponse;
import com.example.demo.diary.service.DiaryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/diarys")
public class DiaryController {

  private final DiaryService diaryService;

  //생성
  @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DiaryCreateResponse> createDiary(@RequestPart("request") DiaryCreateRequest request, @RequestPart(value = "files", required = false) List<MultipartFile> files) {

    DiaryCreateResponse response = diaryService.createDiary(request, files);
    return ResponseEntity.ok(response);
  }

  //상세조회
  @GetMapping("/page/detail")
  public ResponseEntity<DiaryReadResponse> getDiary(@RequestParam("did") Long did) {
    DiaryReadResponse diary = diaryService.getDiary(did);
    return ResponseEntity.ok(diary);
  }

  //페이지리스트 조회
  @GetMapping("/page")
  public ResponseEntity<DiaryPageResponse> getDiariesPage(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {

    int totalRows = diaryService.countDiaries();
    Pager pager = new Pager(6, 5, totalRows, pageNo);

    DiaryPageResponse response = diaryService.getDiariesPage(pager);

    return ResponseEntity.ok(response);
  }

  //수정
  @PutMapping("/update")
  public ResponseEntity<Void> updateDiary(@RequestBody DiaryUpdateRequest request) {
      diaryService.updateDiary(request);
      log.info("request{}", request);
      return ResponseEntity.noContent().build();
  }

  //삭제
  @DeleteMapping("/delete/{did}")
  public ResponseEntity<Void> deleteDiary(@PathVariable("did") Long did) {
    diaryService.deleteDiary(did);
    return ResponseEntity.noContent().build();
  }
}
