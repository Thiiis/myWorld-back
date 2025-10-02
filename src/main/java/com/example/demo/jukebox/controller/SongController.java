package com.example.demo.jukebox.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.interceptor.Login;
import com.example.demo.jukebox.dto.SongCreateRequest;
import com.example.demo.jukebox.dto.SongCreateResponse;
import com.example.demo.jukebox.dto.SongSearchResponse;
import com.example.demo.jukebox.service.SongService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {
  private final SongService songService;

  @Login
  @PostMapping("/create")
  public ResponseEntity<SongCreateResponse> songCreate(
      @RequestBody SongCreateRequest dto,
      HttpServletRequest request) {

    Long loginMid = (Long) request.getAttribute("loginMid");
    SongCreateResponse response = songService.create(loginMid, dto);
    return ResponseEntity.ok(response);
  }

  // 내 음악 목록
  @Login
  @GetMapping("/mysongs")
  public ResponseEntity<List<SongSearchResponse>> getMySongs(HttpServletRequest request) {
    Long loginMid = (Long) request.getAttribute("loginMid");
    List<SongSearchResponse> list = songService.selectMySongsByMid(loginMid);
    return ResponseEntity.ok(list);
  }

  @Login
  @GetMapping("/search")
  public ResponseEntity<List<SongSearchResponse>> searchSongs(
      @RequestParam("query") String query,
      HttpServletRequest request) {
    
    Long loginMid = (Long) request.getAttribute("loginMid");
    List<SongSearchResponse> list = songService.searchSongs(loginMid, query);
    return ResponseEntity.ok(list);
  }

  @Login
  @DeleteMapping("/delete/{sid}")
  public ResponseEntity<Void> jukeboxDelete(
      @PathVariable("sid") Long sid,
      HttpServletRequest request) {

    Long loginMid = (Long) request.getAttribute("loginMid");
    songService.delete(loginMid, sid);
    return ResponseEntity.noContent().build();
  }

}
