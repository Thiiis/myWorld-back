package com.example.demo.profile.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.dto.ProfileInfo;
import com.example.demo.interceptor.Login;
import com.example.demo.jukebox.dto.JukeboxSelectRequest;
import com.example.demo.jukebox.dto.JukeboxSelectResponse;
import com.example.demo.profile.dto.ProfileReadResponse;
import com.example.demo.profile.dto.ProfileUpdateRequest;
import com.example.demo.profile.service.ProfileService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/info/{account}")
    public ResponseEntity<ProfileInfo> getProfileInfo(@PathVariable("account") String account) {
        ProfileInfo result = profileService.getProfileInfoByAccount(account);
        return ResponseEntity.ok(result);
    }

    @Login
    @GetMapping("/detail/me")
    public ResponseEntity<ProfileReadResponse> getMyProfile(HttpServletRequest request) {
        Long loginMid = (Long) request.getAttribute("loginMid");
        ProfileReadResponse result = profileService.getProfileByMid(loginMid);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/detail/{account}")
    public ResponseEntity<ProfileReadResponse> getAnotherProfile(@PathVariable("account") String account) {
        ProfileReadResponse result = profileService.getProfileByAccount(account);
        return ResponseEntity.ok(result);
    }

    @Login
    @PutMapping("/update")
    public ResponseEntity<Void> updateProfile(HttpServletRequest request,
            @RequestBody ProfileUpdateRequest dto) { // JSON 본문을 DTO로 받음

        Long loginMid = (Long) request.getAttribute("loginMid");
        profileService.update(loginMid, dto);

        return ResponseEntity.noContent().build();
    }

    @Login
    @PutMapping("/update/image")
    public ResponseEntity<String> updateProfileImage(HttpServletRequest request,
            @RequestParam("file") MultipartFile file) {
        Long loginMid = (Long) request.getAttribute("loginMid");

        try {
            // 서비스 계층의 단일 메서드 호출로 모든 로직 처리
            String newImageUrl = profileService.updateImage(loginMid, file);
            return ResponseEntity.ok("프로필 이미지가 성공적으로 업데이트되었습니다. URL: " + newImageUrl);

        } catch (IllegalArgumentException e) {
            // 서비스에서 발생시킨 유효성 검사 예외 처리
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            // 파일 저장 중 발생한 예외 처리
            e.printStackTrace(); // 서버 로그에 에러 기록
            return ResponseEntity.internalServerError().body("파일 업로드 중 오류가 발생했습니다.");
        }
    }


    // 선택된 주크박스 저장
    @PostMapping("/{account}/jukebox")
    public ResponseEntity<Void> updateSelectedJukebox(
            @PathVariable("account") String account,
            @RequestBody(required = false) JukeboxSelectRequest dto) {

        profileService.updateProfileJukebox(account, dto.getJid());
        return ResponseEntity.ok().build();
    }

    // 선택된 주크박스 조회
    @GetMapping("/{account}/jukebox")
    public ResponseEntity<JukeboxSelectResponse> getSelectedJukebox(@PathVariable("account") String account) {
        JukeboxSelectResponse jukebox = profileService.getSelectedJukebox(account);
        return ResponseEntity.ok(jukebox);
    }

}
