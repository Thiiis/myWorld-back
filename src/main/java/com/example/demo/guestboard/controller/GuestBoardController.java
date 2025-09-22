package com.example.demo.guestboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.guestboard.dto.GuestBoard;
import com.example.demo.guestboard.service.GuestBoardService;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@RestController
public class GuestBoardController {

  private final GuestBoardService guestBoardService;

  // 방명록 생성
  @PostMapping("/guestBoard-create")
  public ResponseEntity<GuestBoard> guestBoardCreate(@RequestBody GuestBoard guestBoard) {
      GuestBoard created = guestBoardService.create(guestBoard);
      if(created != null) {
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
      } else {
        return ResponseEntity.badRequest().build();
      }
  }

  // 방명록 조회
  @GetMapping("/guestBoard-list")
  public ResponseEntity<List<GuestBoard>> guestBoardList(
      @RequestParam(value = "offset", defaultValue = "0") Long offset, 
      @RequestParam(value = "limit", defaultValue = "0") Long limit) {

    List<GuestBoard> list = guestBoardService.getGuestBoardList(offset, limit);

    if(list.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(list);
    }
  }

  // 방명록 수정
  @PutMapping("/guestBoard-update")
  public ResponseEntity<GuestBoard> guestBoardUpdate(@RequestBody GuestBoard guestBoard) {
    Long rows = guestBoardService.update(guestBoard);
    if(rows > 0) {
      GuestBoard dbGuestBoard = guestBoardService.getGuestBoard(guestBoard.getGbid());
      return ResponseEntity.ok(dbGuestBoard);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  // 방명록 삭제
  @DeleteMapping("/guestBoard-delete")
  public ResponseEntity<GuestBoard> guestBoardDelete(@RequestParam("gbid") Long gbid) {
    GuestBoard dbGuestBoard = guestBoardService.getGuestBoard(gbid);
    if(dbGuestBoard == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    Long rows = guestBoardService.delete(gbid);
    if(rows > 0) {
      return ResponseEntity.ok(dbGuestBoard);
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

  }
    
  
}