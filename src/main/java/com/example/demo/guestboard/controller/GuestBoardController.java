package com.example.demo.guestboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.guestboard.dto.GuestBoardCreateRequest;
import com.example.demo.guestboard.dto.GuestBoardCreateResponse;
import com.example.demo.guestboard.dto.GuestBoardListRequest;
import com.example.demo.guestboard.dto.GuestBoardListResponse;
import com.example.demo.guestboard.dto.GuestBoardUpdateRequest;
import com.example.demo.guestboard.service.GuestBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guestboards")
public class GuestBoardController {

  private final GuestBoardService guestBoardService;

  // 방명록 생성
  @PostMapping("/create")
  public ResponseEntity<GuestBoardCreateResponse> guestBoardCreate(@RequestBody GuestBoardCreateRequest request) {
    GuestBoardCreateResponse response = guestBoardService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  // 방명록 조회
  @GetMapping("/list")
  public ResponseEntity<List<GuestBoardListResponse>> guestBoardList(
      @RequestParam(defaultValue = "0") Long offset,
      @RequestParam(defaultValue = "10") Long limit) {

    GuestBoardListRequest request = new GuestBoardListRequest(offset, limit);
    List<GuestBoardListResponse> list = guestBoardService.getGuestBoardList(request);

    if (list.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(list);
    }
  }

  // 방명록 수정
  @PutMapping("/update")
  public ResponseEntity<Long> guestBoardUpdate(@RequestBody GuestBoardUpdateRequest request) {
    try {
      Long rows = guestBoardService.update(request);
      return ResponseEntity.ok(rows);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0L);
    }
  }

  // 방명록 삭제
  @DeleteMapping("/delete")
  public ResponseEntity<Long> guestBoardDelete(@RequestParam("gbid") Long gbid) {
    Long rows = guestBoardService.delete(gbid);
    if (rows > 0) {
      return ResponseEntity.ok(rows);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0L);
    }

  }

}