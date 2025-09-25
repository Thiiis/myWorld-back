package com.example.demo.jukebox.controller;

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

import com.example.demo.jukebox.dto.JukeboxCreateRequest;
import com.example.demo.jukebox.dto.JukeboxCreateResponse;
import com.example.demo.jukebox.dto.JukeboxDetailResponse;
import com.example.demo.jukebox.dto.JukeboxListResponse;
import com.example.demo.jukebox.dto.JukeboxUpdateRequest;
import com.example.demo.jukebox.service.JukeboxLikesService;
import com.example.demo.jukebox.service.JukeboxService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jukeboxes")
public class JukeboxController {

    private final JukeboxService jukeboxservice;
    private final JukeboxLikesService jukeboxLikesService;

    // 주크박스 생성
    @PostMapping("/create")
    public ResponseEntity<JukeboxCreateResponse> jukeboxCreate(@RequestBody JukeboxCreateRequest request) {
        try {
            JukeboxCreateResponse response = jukeboxservice.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 주크박스 조회
    @GetMapping("/list")
    public ResponseEntity<List<JukeboxListResponse>> jukeboxList(@RequestParam Long mid) {
        List<JukeboxListResponse> list = jukeboxservice.getJukeboxList(mid);
        return ResponseEntity.ok(list);
    }

    // 주크박스 상세조회
    @GetMapping("/detail")
    public ResponseEntity<JukeboxDetailResponse> jukeboxDetail(@RequestParam Long jid) {
        JukeboxDetailResponse response = jukeboxservice.getJukeboxDetail(jid);
        return ResponseEntity.ok(response);
    }
    

    // 주크박스 수정
    @PutMapping("/update")
    public ResponseEntity<JukeboxUpdateRequest> jukeboxUpdate(@RequestBody JukeboxUpdateRequest request) {
        Long rows = jukeboxservice.update(request);
        if (rows > 0) {
            return ResponseEntity.ok(request);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 주크박스 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Long> jukeboxDelete(@RequestParam Long jid) {
        Long rows = jukeboxservice.delete(jid);
        if (rows > 0) {
            return ResponseEntity.ok(rows);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0L);
        }
    }

    // 주크박스 좋아요
    @PostMapping("/like")
    public ResponseEntity<String> toggleJukeboxLike(@RequestParam Long jid, @RequestParam Long mid) { 
        boolean isLike = jukeboxLikesService.toggleLike(jid, mid);
        if(isLike) {
            return ResponseEntity.ok("좋아요");
        } else {
            return ResponseEntity.ok("좋아요 취소");
        }
    }
    

}
