// package com.example.demo.diary.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.diary.dto.request.DiaryCommentRequest;
// import com.example.demo.diary.dto.response.DiaryCommentResponse;
// import com.example.demo.diary.service.DiaryCommentService;

// import lombok.extern.slf4j.Slf4j;


// @Slf4j
// @RestController
// @RequestMapping("/diaries/{did}/comment")
// public class DiaryCommentController {
  
//   @Autowired
//   private DiaryCommentService diaryCommentService;

//   // 댓글 생성
//   @PostMapping("/create")
//   public DiaryCommentResponse createDiaryComment(@PathVariable("did") Long did, @RequestBody DiaryCommentRequest dto) {
//     DiaryCommentResponse response = diaryCommentService.createDiaryComment(did,dto);
//     return response;
//   }

//   // 댓글 읽기
//   @GetMapping("/{dcid}")
//   public DiaryCommentResponse getMethodName(@PathVariable("did") Long did, @PathVariable("dcid") Long dcid) {
//     DiaryCommentResponse response = diaryCommentService.getDiaryComment(did, dcid);
//     return response;
//   }

//   // 댓글 수정
//   @PutMapping("/{dcid}")
//   public void updateDiaryComment(@PathVariable("did") Long did, @PathVariable("dcid") Long dcid, @RequestBody DiaryCommentRequest dto) {
//     diaryCommentService.updateDiaryComment(did, dcid, dto);
//   }

//   // 댓글 삭제
//   @DeleteMapping("{dcid}")
//   public void deleteDiaryComment(@PathVariable("did") Long did, @PathVariable("dcid") Long dcid) {
//     diaryCommentService.deleteDiaryComment(did, dcid);
//   }
  
// }
