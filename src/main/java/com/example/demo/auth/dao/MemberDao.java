package com.example.demo.auth.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.auth.dto.Member;
import com.example.demo.auth.dto.Member;
import com.example.demo.auth.dto.SignupRequest;


@Mapper
public interface MemberDao {
    public int insert(Member member);
    public Member selectByMid(Long mid);
    public Member selectByAccount(String account);
    public int update(Member member);
    public int deleteByMid(Long mid);
    public int deleteByAccount(String account);

    // MemberMapper.xml의 id="existsByAccount" 쿼리를 호출합니다.
    public int existsByAccount(String account);

    // MemberMapper.xml의 id="save" 쿼리를 호출합니다.
    public void save(SignupRequest signupRequest);
    
}