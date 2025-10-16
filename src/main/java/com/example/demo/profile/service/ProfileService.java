package com.example.demo.profile.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.auth.dao.MemberDao;
import com.example.demo.auth.dto.Member;
import com.example.demo.common.dto.ProfileInfo;
import com.example.demo.jukebox.dao.JukeboxDao;
import com.example.demo.jukebox.dto.Jukebox;
import com.example.demo.jukebox.dto.JukeboxSelectResponse;
import com.example.demo.profile.dao.ProfileDao;
import com.example.demo.profile.dto.Profile;
import com.example.demo.profile.dto.ProfileReadResponse;
import com.example.demo.profile.dto.ProfileUpdateRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileDao profileDao;
    private final MemberDao memberDao;
    private final JukeboxDao jukeboxDao;
    // Read

    public ProfileInfo getProfileInfoByAccount(String account) {
        Member member = memberDao.selectByAccount(account);
        if (member == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다: " + account);
        }

        Profile profile = profileDao.selectByMid(member.getMid());
        if (profile == null) {
            throw new IllegalArgumentException("프로필을 찾을 수 없습니다: mid " + member.getMid());
        }
        ;
        return new ProfileInfo(member.getMid(), profile.getNickname(), profile.getBirthdate(), profile.getImgUrl(),
                profile.getStatusMessage());
    }

    public ProfileReadResponse getProfileByAccount(String account) {
        Member member = memberDao.selectByAccount(account);
        if (member == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다: " + account);
        }

        Profile profile = profileDao.selectByMid(member.getMid());
        if (profile == null) {
            throw new IllegalArgumentException("프로필을 찾을 수 없습니다: mid " + member.getMid());
        }
        ;
        return new ProfileReadResponse(profile);
    }

    public ProfileReadResponse getProfileByMid(Long mid) {
        Profile profile = profileDao.selectByMid(mid);
        if (profile == null) {
            throw new IllegalArgumentException("존재하지 않는 프로필입니다.");
        }
        // 생성자를 통해 DTO로 변환
        return new ProfileReadResponse(profile);
    }

    public ProfileReadResponse getProfileByPid(Long pid) {
        Profile profile = profileDao.selectByPid(pid);
        if (profile == null) {
            throw new IllegalArgumentException("존재하지 않는 프로필입니다.");
        }
        // 생성자를 통해 DTO로 변환
        return new ProfileReadResponse(profile);
    }

    // Update
    public void update(Long mid, ProfileUpdateRequest dto) {
        int updatedRows = profileDao.update(mid, dto);
        if (updatedRows == 0) {
            // 업데이트가 실패한 경우 (해당 mid의 프로필이 없는 경우) 예외 처리
            throw new IllegalArgumentException("프로필 업데이트에 실패했습니다. 사용자를 찾을 수 없습니다.");
        }

    }


    @Value("${file.upload-dir}")
    private String uploadDir;
    
    // 이미지 파일 정보를 DB에 업데이트하는 서비스 메서드
    @Transactional // DB 업데이트와 파일 저장을 하나의 트랜잭션으로 묶는 것이 좋습니다.
    public String updateImage(Long mid, MultipartFile file) throws IOException {
        // 1. 파일 유효성 검사 (서비스 계층에서 책임)
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("이미지 파일을 선택해주세요.");
        }

        // 2. 고유한 파일 이름 생성
        String originalFilename = file.getOriginalFilename();
        String storedFilename = UUID.randomUUID().toString() + "_" + originalFilename;

        // 3. 지정된 경로에 파일 저장
        // File.separator는 OS에 맞는 경로 구분자(\ 또는 /)를 자동으로 넣어줍니다.
        String fullPath = uploadDir + File.separator + storedFilename;
        file.transferTo(new File(fullPath));

        // 4. DB에 저장할 URL 생성
        String imageUrl = "/images/" + storedFilename;

        // 5. DB에 파일 정보 업데이트
        int updatedRows = profileDao.updateImage(mid, storedFilename, imageUrl);
        if (updatedRows == 0) {
            // DB 업데이트 실패 시, 방금 저장한 파일을 삭제하는 로직을 추가할 수도 있습니다.
            throw new IllegalArgumentException("프로필 이미지 업데이트에 실패했습니다. 사용자를 찾을 수 없습니다.");
        }

        return imageUrl; // 성공 시 이미지 URL 반환
    }

    // 주크박스 선택
    public void updateProfileJukebox(String account, Long jid) {
        Member member = memberDao.selectByAccount(account);
        profileDao.updateProfileJukebox(member.getMid(), jid);
    }

    // 선택된 주크박스 조회
    public JukeboxSelectResponse getSelectedJukebox(String account) {
        Member member = memberDao.selectByAccount(account);
        Long jid = profileDao.selectByMid(member.getMid()).getJid();
        if (jid == null)
            return null;

        Jukebox jukebox = jukeboxDao.selectByJid(jid);
        if (jukebox == null)
            return null;

        return new JukeboxSelectResponse(jukebox.getJid(), jukebox.getTitle());
    }

}
