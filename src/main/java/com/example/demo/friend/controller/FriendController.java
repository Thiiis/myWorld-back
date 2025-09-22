package com.example.demo.friend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.friend.dto.FriendCreateRequest;
import com.example.demo.friend.dto.FriendResponse;
import com.example.demo.friend.service.FriendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 친구 요청
    @PostMapping("/request")
    public ResponseEntity<FriendResponse> createFriend(@RequestBody FriendCreateRequest dto) {
        FriendResponse friendResponse = friendService.createFriend(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(friendResponse);
    }

}


