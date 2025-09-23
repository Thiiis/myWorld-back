package com.example.demo.diary.controllier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.dto.Member;
import com.example.demo.diary.dto.Diary;
import com.example.demo.diary.dto.DiaryRequest;
import com.example.demo.diary.dto.DiaryResponse;
import com.example.demo.diary.service.DiaryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController("/diarys")
public class DiaryController {
    private final DiaryService diaryService;
    @PostMapping("/create")
    public Diary createDiary(Diary diary){
        return diary;
    }
    @PostMapping(value = "/create")
      public ResponseEntity<DiaryResponse> createDiary(DiaryRequest request){
        Diary diary = new Diary(request.getMid(),request.getTitle(),request.getContent(),request.getViewScope(),request.getEmo(),request.getWeather());
        diaryService.create(diary);
        DiaryResponse response = DiaryResponse.success(diary.getTitle());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
      }
}
