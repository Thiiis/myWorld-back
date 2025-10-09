package com.example.demo.diary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.diary.dto.request.DiarysCommentRequest;
import com.example.demo.diary.dto.response.DiarysCommentResponse;
import com.example.demo.diary.service.DiarysCommentService;
import com.example.demo.interceptor.Login;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RestController
@RequiredArgsConstructor // 생성자 주입 - 스프링 공식문서에서 생성자 주입을 권장(테스트하기 어렵고 순환 의존성 문제가 생길 수 있기 때문)
@RequestMapping("/diaries/{did}/comment")
public class DiarysCommentController {

  private final DiarysCommentService diaryCommentService;

  // 댓글 생성
  @Login
  @PostMapping("/create")
  public ResponseEntity<DiarysCommentResponse> createDiaryComment(@PathVariable("did") Long did,
      @RequestBody DiarysCommentRequest dto, HttpServletRequest request) {
    Long mid = (Long) request.getAttribute("loginMid");
    DiarysCommentResponse response = diaryCommentService.createDiaryComment(did, mid, dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  // 댓글 상세 읽기
  @Login
  @GetMapping("/{dcid}")
  public ResponseEntity<DiarysCommentResponse> getDiaryComment(@PathVariable("did") Long did,
      @PathVariable("dcid") Long dcid, HttpServletRequest request) {
        Long mid = (Long) request.getAttribute("loginMid");
    DiarysCommentResponse response = diaryCommentService.getDiaryComment(did, dcid, mid);
    return ResponseEntity.ok(response);
  }

  // 댓글 리스트 읽기
  @Login
  @GetMapping("/list")
  public ResponseEntity<List<DiarysCommentResponse>> getDiaryComments(@PathVariable("did") Long did, HttpServletRequest request) {
    Long mid = (Long) request.getAttribute("loginMid");
    List<DiarysCommentResponse> response = diaryCommentService.getCommentsByDid(did, mid);
    return ResponseEntity.ok(response);
  }

  // 댓글 수정
  @Login
  @PutMapping("/{dcid}") 
  public ResponseEntity<DiarysCommentResponse> updateDiaryComment(
    @PathVariable("did") Long did,
    @PathVariable("dcid") Long dcid,
    @RequestBody DiarysCommentRequest dto,
    HttpServletRequest request) {

    log.info("PUT /diaries/{}/comment/{} 호출, DTO: {}", did, dcid, dto);

    Long mid = (Long) request.getAttribute("loginMid");
    DiarysCommentResponse updated = diaryCommentService.updateDiaryComment(did, mid, dcid, dto);

    return ResponseEntity.ok(updated); // ✅ 수정된 댓글 정보를 그대로 반환
  }

  // 댓글 삭제
  @Login
  @DeleteMapping("/{dcid}")
  public ResponseEntity<Void> deleteDiaryComment(@PathVariable("did") Long did, @PathVariable("dcid") Long dcid, HttpServletRequest request) {
    Long mid = (Long) request.getAttribute("loginMid");
    diaryCommentService.deleteDiaryComment(did, mid, dcid);
    return ResponseEntity.noContent().build();
  }
}