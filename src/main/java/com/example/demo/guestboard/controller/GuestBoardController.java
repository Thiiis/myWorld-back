package com.example.demo.guestboard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.example.demo.interceptor.Login;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guestboards")
public class GuestBoardController {

  private final GuestBoardService guestBoardService;

  // 방명록 생성
  @Login
  @PostMapping("/create")
  public ResponseEntity<GuestBoardCreateResponse> guestBoardCreate(@RequestBody GuestBoardCreateRequest dto) {
    GuestBoardCreateResponse response = guestBoardService.create(dto);
    return ResponseEntity.ok(response);
  }

  // 방명록 조회
  @GetMapping("/list")
  public ResponseEntity<List<GuestBoardListResponse>> guestBoardList(
      @RequestParam(defaultValue = "0") Long offset,
      @RequestParam(defaultValue = "10") Long limit) {

    GuestBoardListRequest dto = new GuestBoardListRequest(offset, limit);
    List<GuestBoardListResponse> list = guestBoardService.getGuestBoardList(dto);

    if (list.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(list);
    }
  }

  // 방명록 수정
  @Login
  @PutMapping("/update")
  public ResponseEntity<Void> guestBoardUpdate(@RequestBody GuestBoardUpdateRequest dto) {
    guestBoardService.update(dto);
    return ResponseEntity.noContent().build();
  }

  // 방명록 삭제
  @Login
  @DeleteMapping("/delete/{gbid}")
  public ResponseEntity<Void> guestBoardDelete(@PathVariable("gbid") Long gbid) {
    guestBoardService.delete(gbid);
    return ResponseEntity.noContent().build();

  }

}