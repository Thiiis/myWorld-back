package com.example.demo.guestboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.guestboard.dto.GuestBoard;
import com.example.demo.guestboard.service.GuestBoardService;



@RestController
public class GuestBoardController {
  @Autowired
  private GuestBoardService guestBoardService;

  // 방명록 생성
  @PostMapping("/guestBoard-create")
  public GuestBoard guestBoardCreate(@RequestBody GuestBoard guestBoard) {
      return guestBoardService.create(guestBoard);
  }

  // 방명록 조회
  @GetMapping("/guestBoard-list")
  public List<GuestBoard> guestBoardList(@RequestParam(value = "offset", defaultValue = "0") int offset, @RequestParam(value = "limit", defaultValue = "0") int limit) {
    return guestBoardService.getGuestBoardList(offset, limit);
  }
  
  
}