// package com.example.demo.diary.controller;

// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.diary.dto.Pager;
// import com.example.demo.diary.dto.request.DiaryRequest;
// import com.example.demo.diary.dto.response.DiaryResponse;
// import com.example.demo.diary.service.DiaryService;

// import lombok.AllArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @RestController
// @RequestMapping("/diaries")
// @AllArgsConstructor
// public class DiaryController {

//   private final DiaryService diaryService;

//   // 일기 생성
//   @PostMapping("/create")
//   public DiaryResponse createDiary(@RequestPart("request") DiaryRequest dto) {
//     DiaryResponse response = diaryService.createDiary(dto);
//     return response;
//   }

//   // 일기 상세조회
//   @GetMapping("/page/detail")
//   public ResponseEntity<DiaryResponse> getDiary(@RequestParam("did") Long did) {
//     DiaryResponse diary = diaryService.getDiary(did);
//     return ResponseEntity.ok(diary);
//   }

//   // 일기 페이지리스트 조회
//   @GetMapping("/page")
//   public ResponseEntity<List<DiaryResponse>> getDiariesPage(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {

//     int totalRows = diaryService.countDiaries();
//     Pager pager = new Pager(6, 5, totalRows, pageNo);

//     List<DiaryResponse> responses = diaryService.getDiariesPage(pager);

//     return ResponseEntity.ok(responses);
//   }

//   // 일기 수정
//   @PutMapping(value = "/update", consumes = "multipart/form-data")
//   public ResponseEntity<Void> updateDiary(@RequestPart("dto") DiaryRequest dto) {

//     diaryService.updateDiary(dto);

//     return ResponseEntity.noContent().build();
//   }

//   // 일기 단일 삭제
//   @DeleteMapping("/delete")
//   public ResponseEntity<Void> deleteDiary(@PathVariable("did") Long did) {
//     diaryService.deleteDiary(did);
//     return ResponseEntity.noContent().build();
//   }

//   // 일기 다중 삭제(여러 아이디를 받을 때에는 쿼리 파라미터를 사용하는 것이 일반적)
//   @DeleteMapping("/delete-list")
//   public ResponseEntity<Void> deleteDiaries(@RequestParam("did") List<Long> dids) {
//     diaryService.deleteDiaries(dids);
//     return ResponseEntity.noContent().build();
//   }

// }