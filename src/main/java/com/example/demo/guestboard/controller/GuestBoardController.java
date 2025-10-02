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

import com.example.demo.auth.dto.Member;
import com.example.demo.auth.dto.MemberReadResponse;
import com.example.demo.auth.service.MemberService;
import com.example.demo.guestboard.dto.GuestBoardCreateRequest;
import com.example.demo.guestboard.dto.GuestBoardCreateResponse;
import com.example.demo.guestboard.dto.GuestBoardListRequest;
import com.example.demo.guestboard.dto.GuestBoardListResponse;
import com.example.demo.guestboard.dto.GuestBoardUpdateRequest;
import com.example.demo.guestboard.service.GuestBoardService;
import com.example.demo.interceptor.Login;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guestboards")
public class GuestBoardController {

  private final GuestBoardService guestBoardService;
  private final MemberService memberService;

  // 방명록 생성
  @Login
  @PostMapping("/create/{hostaccount}")
  public ResponseEntity<GuestBoardCreateResponse> guestBoardCreate(
      @PathVariable("hostaccount") String hostaccount,
      @RequestBody GuestBoardCreateRequest dto,
      HttpServletRequest request) {

    MemberReadResponse host = memberService.getMember(hostaccount);
    Long hostid = host.getMid();

    Long loginMid = (Long) request.getAttribute("loginMid");
    GuestBoardCreateResponse response = guestBoardService.create(hostid, loginMid, dto);
    return ResponseEntity.ok(response);
  }

  // 방명록 조회
  @GetMapping("/list/{hostaccount}")
  public ResponseEntity<List<GuestBoardListResponse>> guestBoardList(
      @PathVariable("hostaccount") String hostaccount,
      @RequestParam(name="offset", defaultValue = "0") Long offset,
      @RequestParam(name="limit", defaultValue = "10") Long limit) {

    MemberReadResponse host = memberService.getMember(hostaccount);
    Long hostid = host.getMid();

    GuestBoardListRequest dto = new GuestBoardListRequest(hostid, offset, limit);
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
  public ResponseEntity<Void> guestBoardUpdate(@RequestBody GuestBoardUpdateRequest dto, HttpServletRequest request) {

    Long loginMid = (Long) request.getAttribute("loginMid");
    guestBoardService.update(loginMid, dto);
    return ResponseEntity.noContent().build();
  }

  // 방명록 삭제
  @Login
  @DeleteMapping("/delete/{gbid}")
  public ResponseEntity<Void> guestBoardDelete(@PathVariable("gbid") Long gbid, HttpServletRequest request) {

    Long loginMid = (Long) request.getAttribute("loginMid");
    guestBoardService.delete(gbid, loginMid);
    return ResponseEntity.noContent().build();
  }

}