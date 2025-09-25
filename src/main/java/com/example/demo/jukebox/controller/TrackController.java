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

import com.example.demo.jukebox.dto.TrackCreateRequest;
import com.example.demo.jukebox.dto.TrackCreateResponse;
import com.example.demo.jukebox.dto.TrackListResponse;
import com.example.demo.jukebox.service.TrackService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/tracks")
public class TrackController {
  private final TrackService trackService;

  @PostMapping("/create")
  public ResponseEntity<TrackCreateResponse> trackCreate(@RequestBody TrackCreateRequest request) {
    TrackCreateResponse response = trackService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/list")
  public List<TrackListResponse> trackList(@RequestParam Long jid) {
    return trackService.getTrackListByJid(jid);
  }
  

  @DeleteMapping("delete")
  public ResponseEntity<Long> trackDelete(@RequestParam Long trid) {
    Long rows = trackService.delete(trid);
        if (rows > 0) {
            return ResponseEntity.ok(rows);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0L);
        }
  }
  
}
