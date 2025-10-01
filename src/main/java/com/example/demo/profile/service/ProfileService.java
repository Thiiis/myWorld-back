package com.example.demo.profile.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    // Read
    public ProfileReadResponse getProfile(Long pid) {
        Profile profile = profileDao.selectByPid(pid);
        if (profile == null) {
            throw new IllegalArgumentException("존재하지 않는 프로필입니다.");
        }
        return new ProfileReadResponse(profile.getMid(), profile.getMid(),
                profile.getNickname(), profile.getBirthdate(), profile.getImgName(), profile.getImgUrl(),
                profile.getStatusMessage(), profile.getIntro(), profile.getMainAddress());
    }
    public Profile getProfileByMid(Long mid) {
        return profileDao.selectByMid(mid);
    }
    // public Profile getProfileByNickname(String nickname) {
    // return profileDao.selectByNickname(nickname);
    // }

    // Update
    public void update(Long mid, ProfileUpdateRequest dto) {
        profileDao.update(mid, dto);

    }
    // 이미지 파일 정보를 DB에 업데이트하는 서비스 메서드
    public void updateImage(Long mid, String imgName, String imgUrl) {
        // profile.xml에 정의된 updateImage 쿼리를 실행
        profileDao.updateImage(mid, imgName, imgUrl);
    }

}
