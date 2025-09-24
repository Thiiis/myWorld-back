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
@RequestMapping("/diarys")
public class DiaryController {

  private final DiaryService diaryService;

  @PostMapping("/create")
  public ResponseEntity<DiaryResponse> createDiary(@RequestBody DiaryCreateRequest request) {
    diaryService.createDiary(request);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/detail")
  public ResponseEntity<DiaryResponse> getDiary(@RequestParam("did") Long did) {

    DiaryResponse diary = diaryService.getDiary(did);

    return ResponseEntity.ok(diary);
  }

}
