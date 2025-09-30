package com.example.demo.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.service.EmailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admins") // API 경로 설정
public class AdminController {

    private final EmailService emailService;

    // 비밀번호 찾기 (이메일 발송) API
    @PostMapping("/select-password")
    public ResponseEntity<String> selectPassword(@RequestParam String aId, @RequestParam String aEmail) {
        String password = emailService.selectAdminPassword(aId, aEmail);
        if (password != null) {
            return ResponseEntity.ok("입력하신 이메일로 비밀번호 안내 메일이 발송되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("일치하는 관리자 정보가 없습니다.");
        }
    }
}