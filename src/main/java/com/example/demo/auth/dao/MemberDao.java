package com.example.demo.auth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.auth.dto.Member;


@Mapper
public interface MemberDao {
     public void insert(Member member);
     public Member selectByAccount(String account);
     public Member selectByMid(Long mid);
     public void update(@Param("mid") Long mid, @Param("email") String email, @Param("pwd") String pwd);
     public int deleteByMid(Long mid);
     public int countByAccount(String account);
}