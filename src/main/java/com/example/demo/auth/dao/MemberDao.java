package com.example.demo.auth.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.auth.dto.Member;


@Mapper
public interface MemberDao {
     public void insert(Member member);
     public Member selectByAccount(String account);
     public Optional<Member> selectByMid(Long mid);
     public void update(@Param("mid") Long mid, @Param("pwd") String pwd);
     public int deleteByMid(Long mid);
     public int countByAccount(String account);
}