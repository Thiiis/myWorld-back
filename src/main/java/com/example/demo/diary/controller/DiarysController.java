package com.example.demo.diary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.diary.dto.Pager;
import com.example.demo.diary.dto.request.DiarysRequest;
import com.example.demo.diary.dto.response.DiarysResponse;
import com.example.demo.diary.service.DiarysService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/diaries")
@AllArgsConstructor
public class DiarysController {

  private final DiarysService diaryService;

  // 일기 생성
  // @Login
  @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DiarysResponse> createDiary( @RequestPart("dto") DiarysRequest dto, @RequestPart(value = "files", required = false) List<MultipartFile> files) {
    // 1) 일기 저장 + DB insert
    DiarysResponse diaryResponse = diaryService.createDiary(dto, files);
    return ResponseEntity.status(HttpStatus.CREATED).body(diaryResponse);
  }

  // 일기 상세조회
  @GetMapping("/page/detail")
  public ResponseEntity<DiarysResponse> getDiary(@RequestParam("did") Long did) {

    DiarysResponse diary = diaryService.getDiary(did);
    return ResponseEntity.ok(diary);
  }

  // 일기 페이지리스트 조회
  @GetMapping("/page")
  public ResponseEntity<List<DiarysResponse>> getDiariesPage( @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {

    int totalRows = diaryService.countDiaries();
    Pager pager = new Pager(6, 5, totalRows, pageNo);

    List<DiarysResponse> responses = diaryService.getDiariesPage(pager);

    return ResponseEntity.ok(responses);
  }

  // 일기 수정
  @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Void> updateDiary(@RequestPart("dto") DiarysRequest dto, @RequestParam(value = "deleteAids", required = false) List<Long> deleteAids, @RequestPart(value = "addFiles", required = false) List<MultipartFile> addFiles) {
    diaryService.updateDiary(dto, deleteAids, addFiles);
    return ResponseEntity.noContent().build();
  }

  // 일기 단일 삭제
  @DeleteMapping("/delete/{did}")
  public ResponseEntity<Void> deleteDiary(@PathVariable("did") Long did) {
    diaryService.deleteDiary(did);
    return ResponseEntity.noContent().build();
  }

  // 일기 다중 삭제(여러 아이디를 받을 때에는 쿼리 파라미터를 사용하는 것이 일반적)
  @DeleteMapping("/delete-list")
  public ResponseEntity<Void> deleteDiaries(@RequestParam("did") List<Long> dids) {
    diaryService.deleteDiaries(dids);
    return ResponseEntity.noContent().build();
  }

}