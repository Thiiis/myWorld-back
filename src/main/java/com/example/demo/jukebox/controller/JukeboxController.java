package com.example.demo.jukebox.controller;

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
import com.example.demo.interceptor.Login;
import com.example.demo.jukebox.dto.JukeboxCreateRequest;
import com.example.demo.jukebox.dto.JukeboxCreateResponse;
import com.example.demo.jukebox.dto.JukeboxDetailResponse;
import com.example.demo.jukebox.dto.JukeboxListResponse;
import com.example.demo.jukebox.dto.JukeboxUpdateRequest;
import com.example.demo.jukebox.service.JukeboxLikesService;
import com.example.demo.jukebox.service.JukeboxService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/jukeboxes")
public class JukeboxController {

    private final JukeboxService jukeboxservice;
    private final JukeboxLikesService jukeboxLikesService;
    private final MemberService memberService;

    // 주크박스 생성
    @Login
    @PostMapping("/create")
    public ResponseEntity<JukeboxCreateResponse> jukeboxCreate(
            @RequestBody JukeboxCreateRequest dto, 
            HttpServletRequest request) {

        Long loginMid = (Long) request.getAttribute("loginMid");
        JukeboxCreateResponse response = jukeboxservice.create(loginMid, dto);
        return ResponseEntity.ok(response);
    }

    // 주크박스 조회
    @GetMapping("/list")
    public ResponseEntity<List<JukeboxListResponse>> jukeboxList(@RequestParam("account") String account) {
        MemberReadResponse member = memberService.getMember(account);
        List<JukeboxListResponse> list = jukeboxservice.getJukeboxList(member.getMid());
        return ResponseEntity.ok(list);
    }

    // 주크박스 상세조회
    @GetMapping("/detail")
    public ResponseEntity<JukeboxDetailResponse> jukeboxDetail(@RequestParam Long jid) {
        JukeboxDetailResponse response = jukeboxservice.getJukeboxDetail(jid);
        return ResponseEntity.ok(response);
    }

    // 주크박스 수정
    @Login
    @PutMapping("/update")
    public ResponseEntity<Void> jukeboxUpdate(@RequestBody JukeboxUpdateRequest dto, HttpServletRequest request) {
        Long loginMid = (Long) request.getAttribute("loginMid");
        jukeboxservice.update(loginMid, dto);
        return ResponseEntity.noContent().build();
    }

    // 주크박스 삭제
    @Login
    @DeleteMapping("/delete/{jid}")
    public ResponseEntity<Void> jukeboxDelete(@PathVariable("jid") Long jid, HttpServletRequest request) {
        Long loginMid = (Long) request.getAttribute("loginMid");
        jukeboxservice.delete(loginMid, jid);
        return ResponseEntity.noContent().build();
    }

    // 주크박스 좋아요
    @Login
    @PostMapping("/like")
    public ResponseEntity<String> toggleJukeboxLike(@RequestParam("jid") Long jid, HttpServletRequest request) {

        Long loginMid = (Long) request.getAttribute("loginMid");

        boolean isLike = jukeboxLikesService.toggleLike(jid, loginMid);
        if (isLike) {
            return ResponseEntity.ok("좋아요");
        } else {
            return ResponseEntity.ok("좋아요 취소");
        }
    }
}


