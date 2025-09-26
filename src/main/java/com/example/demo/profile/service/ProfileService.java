package com.example.demo.profile.service;

import org.springframework.stereotype.Service;

import com.example.demo.profile.dao.ProfileDao;
import com.example.demo.profile.dto.Profile;
import com.example.demo.profile.dto.ProfileAddressUpdateRequest;
import com.example.demo.profile.dto.ProfileFKUpdateRequest;
import com.example.demo.profile.dto.ProfileReadResponse;
import com.example.demo.profile.dto.ProfileUpdateRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileService {
    private final ProfileDao profileDao;

    // Read
    public ProfileReadResponse getProfileByPid(Long pid) {
        Profile profile = profileDao.selectByPid(pid);
        if (profile == null) {
            throw new IllegalArgumentException("존재하지 않는 프로필입니다.");
        }

        return new ProfileReadResponse(
                profile.getPid(),
                profile.getNickname(),
                profile.getBirthdate(),
                profile.getImgUrl(),
                profile.getStatusMessage());
    }

    public Profile getProfileByMid(Long mid) {
        return profileDao.selectByMid(mid);
    }

    public Profile getProfileByNickname(String nickname) {
        return profileDao.selectByNickname(nickname);
    }

    // Update
    // '주소' 업데이트 요청 처리
    public void updateAddress(ProfileAddressUpdateRequest dto) {
        // 1. Mapper로 보낼 '통합 Request' 생성
        ProfileUpdateRequest unifiedDto = new ProfileUpdateRequest();

        // 2. 필수 값(pid)과 섹션에 맞는 값(address)을 통합 Request에 복사
        unifiedDto.setPid(dto.getPid());
        unifiedDto.setPostalCode(dto.getPostalCode());
        unifiedDto.setMainAddress(dto.getMainAddress());
        unifiedDto.setDetailAddress(dto.getDetailAddress());

        // 3. 여기서도 동일한 updateProfile 메소드를 호출
        // profileDao.update(unifiedDto);
    }

    public void updateFK(ProfileFKUpdateRequest dto) {
        ProfileUpdateRequest unifiedDto = new ProfileUpdateRequest();

        unifiedDto.setPid(dto.getPid());
        unifiedDto.setMid(dto.getMid());
        unifiedDto.setJid(dto.getJid());
        unifiedDto.setTid(dto.getTid());

        // profileDao.update(unifiedDto);
    }

    // Delete
}
