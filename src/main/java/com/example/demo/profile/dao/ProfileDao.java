package com.example.demo.profile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.profile.dto.Profile;

@Mapper
public interface ProfileDao {
    public void insert(Profile profile);
    // public int existsByNickname(String nickname);
    public Profile selectByPid(Long pid);
    public Profile selectByNickname(String nickname);
    public void update(Profile Profile);
    // public void updateImg(Profile Profile);
    // public int updateAddress(Profile profile);
    // public int delete(int pid);
    // public int delete(String pname);
    
}