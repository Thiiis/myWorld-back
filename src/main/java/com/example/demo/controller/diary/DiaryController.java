package com.example.demo.controller.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.diary.Diary;
import com.example.demo.service.diary.DiaryService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class DiaryController {

  @Autowired
  private DiaryService diaryService;
  
  @PostMapping("/diary-create")
  public String diaryCreate(@RequestBody Diary diary) {
    log.info("diary:{}", diary);
      diaryService.createDiary(diary);
      
      return ""+ diary.getDiaryId();
  }

  // @GetMapping("/diary-list")
  // public String getMethodName(@RequestParam String param) {
  //     return new String();
  // }
  
}
