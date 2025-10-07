package com.example.demo.profile.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.dto.ProfileInfo;
import com.example.demo.interceptor.Login;
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

    @GetMapping("/detail")
    public ResponseEntity<ProfileReadResponse> getAnotherProfile(@PathVariable("account") String account) {
        ProfileReadResponse result = profileService.getProfileByAccount(account);
        return ResponseEntity.ok(result);
    }

    @Value("${file.upload-dir}")
    private String uploadDir;
    
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
        // 1. 파일 유효성 검사
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("이미지 파일을 선택해주세요.");
        }

        try {
            // 2. 고유한 파일 이름 생성 (파일 이름 중복 방지)
            String originalFilename = file.getOriginalFilename();
            String storedFilename = UUID.randomUUID().toString() + "_" + originalFilename;

            // 3. 지정된 경로에 파일 저장
            // File.separator는 OS에 맞는 경로 구분자(\ 또는 /)를 자동으로 넣어줍니다.
            String fullPath = uploadDir + File.separator + storedFilename;
            file.transferTo(new File(fullPath));

            // 4. DB에 저장할 URL 생성
            String imageUrl = "/images/" + storedFilename;

            // 5. 서비스 계층을 호출하여 DB에 파일 정보(저장된 이름, URL) 업데이트
            profileService.updateImage(loginMid, storedFilename, imageUrl);

            return ResponseEntity.ok("프로필 이미지가 성공적으로 업데이트되었습니다.");

        } catch (IOException e) {
            e.printStackTrace(); // 서버 로그에 에러 기록
            return ResponseEntity.internalServerError().body("파일 업로드 중 오류가 발생했습니다.");
        }

    }
}

