package com.example.demo.diary.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.example.demo.diary.dto.DiaryRequest;
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
    public Map<String, Object> createDiary(@Validated(DiaryRequest.Create.class) @RequestPart("diary") Diary diary, @RequestPart(value = "files", required = false) List<MultipartFile> files) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 1. 일기 저장
            Diary created = diaryService.createDiary(diary, files);
            map.put("result", "success");
            map.put("diary", created);   // PK(did) 포함된 JSON 응답
        } catch (Exception e) {
            log.error("Diary insert error", e);
            map.put("result", "fail");
            map.put("message", e.getMessage());
        }
        return map;
    }

  @GetMapping("/detail")
  public Map<String, Object> getDiary(@RequestParam("did") int did) {
    Map<String, Object> map = new HashMap<>();
    Diary diary = diaryService.getDiary(did);
    if (diary != null) {
        map.put("result", "success");
        map.put("diary", diary);
    } else {
        map.put("result", "fail");
        map.put("message", "Diary not found");
    }
    return map;
  }

  @GetMapping("/list")
  public Map<String, Object> getAllDiaries() {
    Map<String, Object> map = new HashMap<>();
    List<Diary> diaries = diaryService.getAllDiaries();
    map.put("result", "success");
    map.put("diaries", diaries);
      return map;
  }

  @GetMapping("/list/paged")
  public Map<String, Object> getDiariesPaged(@RequestParam(name="pageNo", defaultValue = "1") int pageNo, @RequestParam(name="rowsPerPage",defaultValue = "6") int rowsPerPage, @RequestParam(name="pagesPerGroup",defaultValue = "5") int pagesPerGroup) {
      return diaryService.getDiariesPaged(pageNo, rowsPerPage, pagesPerGroup);
  }
  

  @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Map<String, Object> updateDiary(@Validated(DiaryRequest.Update.class) @RequestPart("diary") Diary diary, @RequestPart(value = "files", required = false) List<MultipartFile> files, @RequestPart(value = "deleteAids", required = false) List<Integer> deleteAids) {
      Map<String, Object> map = new HashMap<>();
      try{
        int result = diaryService.updateDiaryWithFiles(diary, files, deleteAids);
        if(result > 0) {
            map.put("result", "success");
            map.put("diary", diaryService.getDiary(diary.getDid()));
        } else {
            map.put("result", "fail");
            map.put("message", "Diary not found");
        }
      } catch(Exception e) {
        map.put("result", "fail");
        map.put("message", e.getMessage());
      }
      return map;
  }
  
  // 상세페이지 단건 삭제
  @DeleteMapping("/delete/{did}")
  public String deleteDiary(@PathVariable("did") int did) {
    log.info("did:{}"+ did);
      int result = diaryService.deleteDiary(did);
      
      return result > 0 ? "Delete Success" : "Delete Failed";
  }

  // 페이징 목록에서 여러 개 선택 삭제
  @DeleteMapping("/delete")
  public Map<String, Object> deleteDiaries(@RequestBody List<Integer> didList) {
    int deletedCount = diaryService.deleteDiaries(didList);
    Map<String, Object> map = new HashMap<>();
    map.put("result", "success");
    map.put("deletedCount", deletedCount);
    return map;
  }
}
