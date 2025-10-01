package com.example.demo.profile.service;

import org.springframework.stereotype.Service;

import com.example.demo.auth.dao.MemberDao;
import com.example.demo.auth.dto.Member;
import com.example.demo.common.dto.ProfileInfo;
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
        return new ProfileInfo(profile.getPid(),profile.getNickname(),profile.getImgUrl(),profile.getStatusMessage());
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

    // 이미지 파일 정보를 DB에 업데이트하는 서비스 메서드
    public void updateImage(Long mid, String imgName, String imgUrl) {
        // profile.xml에 정의된 updateImage 쿼리를 실행
        int updatedRows = profileDao.updateImage(mid, imgName, imgUrl);
        if (updatedRows == 0) {
            // 이미지 업데이트가 실패한 경우 예외 처리
            throw new IllegalArgumentException("프로필 이미지 업데이트에 실패했습니다. 사용자를 찾을 수 없습니다.");
        }
    }

}
