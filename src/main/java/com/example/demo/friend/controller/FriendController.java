package com.example.demo.friend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.friend.dto.FriendCreateRequest;
import com.example.demo.friend.dto.FriendCreateResponse;
import com.example.demo.friend.dto.FriendRequestListResponse;
import com.example.demo.friend.service.FriendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 친구 요청
    @PostMapping("/request")
    public ResponseEntity<FriendCreateResponse> createFriend(@RequestBody FriendCreateRequest dto) {
        FriendCreateResponse friendResponse = friendService.createFriend(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(friendResponse);
    }

    // 친구 수락
    @PostMapping("/{id}/accept")
    public ResponseEntity<Void> acceptFriend(@PathVariable("id") Long id) {
        friendService.acceptFriend(id);
        return ResponseEntity.noContent().build();
    }

    //친구 거절
    @PostMapping("{id}/reject")
    public ResponseEntity<Void> rejectFriend(@PathVariable("id") Long id) {
    friendService.rejectFriend(id);
    return ResponseEntity.noContent().build();
    }

    // 친구 요청 조회
    @GetMapping("/request-list")
    //쿼리파람(임시) -> JWT 구현하면 변경
    public ResponseEntity<List<FriendRequestListResponse>> getFriednRequestList(@RequestParam("userId") Long userId) {
        List<FriendRequestListResponse> requests = friendService.getFriednRequestList(userId);
        return ResponseEntity.ok(requests); 
    }

    // @GetMapping("/list/me")


}
