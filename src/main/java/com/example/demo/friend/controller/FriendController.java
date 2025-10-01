package com.example.demo.friend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.example.demo.auth.service.JwtService;
import com.example.demo.friend.dto.FriendCreateRequest;
import com.example.demo.friend.dto.FriendCreateResponse;
import com.example.demo.friend.dto.FriendListResponse;
import com.example.demo.friend.dto.FriendRequestListResponse;
import com.example.demo.friend.service.FriendService;
import com.example.demo.interceptor.Login;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 친구 요청
    @Login
    @PostMapping
    public ResponseEntity<FriendCreateResponse> createFriend(@RequestBody FriendCreateRequest dto, HttpServletRequest request) {
        Long loginMid = (Long) request.getAttribute("loginMid");
        FriendCreateResponse friendResponse = friendService.createFriend(loginMid, dto.getAccId());
        return ResponseEntity.status(HttpStatus.CREATED).body(friendResponse);
    }

    // 친구 수락
    @Login
    @PutMapping("/{fid}/accept")
    public ResponseEntity<Void> acceptFriend(@PathVariable("fid") Long fid, HttpServletRequest request) {
        Long loginMid = (Long) request.getAttribute("loginMid");
        friendService.acceptFriend(fid, loginMid);
        return ResponseEntity.noContent().build();
    }

    //친구 거절
    @Login
    @PutMapping("/{fid}/reject")
    public ResponseEntity<Void> rejectFriend(@PathVariable("fid") Long fid, HttpServletRequest request) {
        Long loginMid = (Long) request.getAttribute("loginMid");
        friendService.rejectFriend(fid, loginMid);
        return ResponseEntity.noContent().build();
    }

    //친구 요청 조회
    @Login
    @GetMapping("/pending")
    public ResponseEntity<List<FriendRequestListResponse>> getFriendRequestList(HttpServletRequest request) {
        Long loginMid = (Long) request.getAttribute("loginMid");
        List<FriendRequestListResponse> requests = friendService.getFriendRequestList(loginMid);
        return ResponseEntity.ok(requests);
    }

    //친구 목록 조회 -> 친구 목록 내 검색은 프론트에서 구현
    @Login
    @GetMapping("/{mid}/list")
    public ResponseEntity<List<FriendListResponse>> getFriendList(@PathVariable("mid") Long mid) {
        List<FriendListResponse> friends = friendService.getFriendList(mid);
        return ResponseEntity.ok(friends);
    }

    //친구 삭제 -> 소프트 딜리트 고민
    @Login
    @DeleteMapping("/{fid}")
    public ResponseEntity<Void> deleteFriend(@PathVariable("fid") Long fid, HttpServletRequest request) {
        Long loginMid = (Long) request.getAttribute("loginMid");
        friendService.deleteFriend(fid, loginMid);
        return ResponseEntity.noContent().build();  
    }
}
