package com.example.demo.profile.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.profile.dto.Profile;
import com.example.demo.profile.dto.ProfileUpdateRequest;

@Mapper
 public interface ProfileDao {
    void insert(Profile profile); //Create

    // Read
    int countByNickname(String nickname);
    Profile selectByPid(Long pid);
    //  Profile selectByMid(Long mid);
    Profile selectByMid(Long mid);
    //  Profile selectByNickname(String nickname);
    // Update    
    int update(@Param("mid") Long mid, @Param("dto") ProfileUpdateRequest dto);
    // 프로필 이미지 정보만 업데이트하는 메서드
    int updateImage(@Param("mid") Long mid, @Param("imgName") String imgName, @Param("imgUrl") String imgUrl);

    
}