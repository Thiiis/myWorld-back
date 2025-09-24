package com.example.demo.jukebox.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jukebox.dto.SongCreateRequest;
import com.example.demo.jukebox.dto.SongCreateResponse;
import com.example.demo.jukebox.dto.SongSearchResponse;
import com.example.demo.jukebox.service.SongService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {
  private final SongService songService;

  @PostMapping("/create")
  public ResponseEntity<SongCreateResponse> songCreate(@RequestBody SongCreateRequest request) {
    SongCreateResponse response = songService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/search")
  public List<SongSearchResponse> searchSongs(@RequestParam String query) {
    return songService.searchSongs(query);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<Long> jukeboxDelete(@RequestParam Long sid) {
    Long rows = songService.delete(sid);
    if (rows > 0) {
      return ResponseEntity.ok(rows);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0L);
    }
  }

}
