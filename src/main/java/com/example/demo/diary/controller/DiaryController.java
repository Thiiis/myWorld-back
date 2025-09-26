package com.example.demo.diary.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/diarys")
public class DiaryController {

  private final DiaryService diaryService;

  //생성
  @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DiaryCreateResponse> createDiary(@RequestPart("request") DiaryCreateRequest dto, @RequestPart(value = "files", required = false) List<MultipartFile> files) {

    DiaryCreateResponse response = diaryService.createDiary(dto, files);
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
  @PutMapping(value = "/update/{did}", consumes = "multipart/form-data")
  public ResponseEntity<Void> updateDiary(@PathVariable("did") Long did, @RequestPart("dto") DiaryUpdateRequest dto, @RequestPart(value = "files", required = false) List<MultipartFile> files) {
    
    dto.updateRequest(did);
    diaryService.updateDiary(dto, files);

    return ResponseEntity.noContent().build();
  }

  // 단일 삭제
  @DeleteMapping("/delete")
  public ResponseEntity<Void> deleteDiary(@PathVariable("did") Long did) {
    diaryService.deleteDiary(did);
    return ResponseEntity.noContent().build();
  }

  // 다중 삭제(여러 아이디를 받을 때에는 쿼리 파라미터를 사용하는 것이 일반적)
  @DeleteMapping("/delete-list")
  public ResponseEntity<Void> deleteDiaries(@RequestParam("did") List<Long> dids) {
    diaryService.deleteDiaries(dids);
    return ResponseEntity.noContent().build();
  }
}
