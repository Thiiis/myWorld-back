// package com.example.demo.diary.controller;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.diary.dto.DiaryComment;
// import com.example.demo.diary.dto.request.DiaryCommentCreateRequest;
// import com.example.demo.diary.dto.request.DiaryCommentUpdateRequest;
// import com.example.demo.diary.dto.response.DiaryCommentCreateResponse;
// import com.example.demo.diary.dto.response.DiaryCommentReadResponse;
// import com.example.demo.diary.service.DiaryCommentService;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.web.bind.annotation.PutMapping;


// @Slf4j
// @RestController
// @RequiredArgsConstructor  //생성자 주입 - 스프링 공식문서에서 생성자 주입을 권장(테스트하기 어렵고 순환 의존성 문제가 생길 수 있기 때문)
// @RequestMapping("/diaries/{did}/comment")
// public class DiaryCommentController {
  
//   private final DiaryCommentService diaryCommentService;

//   // 댓글 생성
//   @PostMapping("/create")
//   public ResponseEntity<DiaryCommentCreateResponse> createDiaryComment(@PathVariable("did") Long did, @RequestBody DiaryCommentCreateRequest dto) {
//     DiaryCommentCreateResponse response = diaryCommentService.createDiaryComment(did,dto);
//     return ResponseEntity.status(HttpStatus.CREATED).body(response);
//   }

//   // 댓글 읽기
//   @GetMapping("/{dcid}")
//   public ResponseEntity<DiaryCommentReadResponse> getMethodName(@PathVariable("did") Long did, @PathVariable("dcid") Long dcid) {
//     DiaryCommentReadResponse response = diaryCommentService.getDiaryComment(did, dcid);
//     return ResponseEntity.ok(response);
//   }

//   // 댓글 수정
//   @PutMapping("/{dcid}")
//   public ResponseEntity<Void> updateDiaryComment(@PathVariable("did") Long did, @PathVariable("dcid") Long dcid, @RequestBody DiaryCommentUpdateRequest dto) {
//     diaryCommentService.updateDiaryComment(did, dcid, dto);
//     return ResponseEntity.noContent().build();
//   }

//   // 댓글 삭제
//   @DeleteMapping("{dcid}")
//   public ResponseEntity<Void> deleteDiaryComment(@PathVariable("did") Long did, @PathVariable("dcid") Long dcid) {
//     diaryCommentService.deleteDiaryComment(did, dcid);
//     return ResponseEntity.noContent().build();
//   }
  
// }
