package com.example.demo.auth.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.auth.dto.Member;
import com.example.demo.common.dto.ProfileInfo;


@Mapper
public interface MemberDao {
     public void insert(Member member);
     public Member selectByAccount(String account);
     public Optional<Member> selectByMid(Long mid);
     List<ProfileInfo> searchMembers(@Param("keyword") String keyword, @Param("loginMid") Long loginMid);
     public void update(@Param("mid") Long mid, @Param("pwd") String pwd);
     public int deleteByMid(Long mid);
     public int countByAccount(String account);
}