package com.example.demo.jukebox.controller;

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

import com.example.demo.jukebox.dto.Jukebox;
import com.example.demo.jukebox.dto.JukeboxCreateRequest;
import com.example.demo.jukebox.dto.JukeboxCreateResponse;
import com.example.demo.jukebox.service.JukeboxService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JukeboxController {
    private final JukeboxService jukeboxservice;

    // 주크박스 생성
    @PostMapping("/jukebox-create")
    public ResponseEntity<JukeboxCreateResponse> jukeboxCreate(@RequestBody JukeboxCreateRequest request) {
        JukeboxCreateResponse response = jukeboxservice.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 주크박스 조회
    @GetMapping("/jukebox-list")
    public List<Jukebox> jukeboxList() {
        List<Jukebox> list = jukeboxservice.getJukeboxList();
        return list;
    }

    // 주크박스 수정
    @PutMapping("/jukebox-update")
    public ResponseEntity<String> jukeboxUpdate(@RequestBody Jukebox jukebox) {
        Long rows = jukeboxservice.update(jukebox);
        if (rows > 0) {
            return ResponseEntity.ok("수정 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터 없음");
        }
    }

    // 주크박스 삭제
    @DeleteMapping("/jukebox-delete")
    public ResponseEntity<String> jukeboxDelete(@RequestParam Long jid) {
        Long rows = jukeboxservice.delete(jid);
        if (rows > 0) {
            return ResponseEntity.ok("삭제 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터 없음");
        }
    }

}
