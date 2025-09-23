package com.example.demo.auth.service;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.demo.auth.dao.MemberDao;
import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.dto.Member;
import com.example.demo.auth.dto.SignupRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
  private final MemberDao memberDao;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder; // SecurityConfig에 등록한 Bean을 주입받음
  // private final JwtService jwtService;

  @Transactional
  public Member signup(SignupRequest signupRequest) {
    int count = memberDao.existsByAccount(signupRequest.getAccount());
    if (count > 0) {
      // 이미 아이디가 존재하면 예외를 발생시킵니다.
      throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
    } else {
      Member member = new Member(signupRequest); // DTO를 Entity로 변환
      memberDao.insert(member);
      return member;
    }
  }

  public String login(LoginRequest loginRequest) {
    Member member = memberDao.selectByAccount(loginRequest.getAccount());

    // 1. 아이디 존재 여부와 비밀번호 일치 여부를 한 번에 확인
    if (member == null || !passwordEncoder.matches(loginRequest.getPwd(), member.getPwd())) {
      // 아이디가 없거나 비밀번호가 틀리면 예외 발생
      throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
    }
    // 2. 로그인이 성공하면 JWT 토큰 생성 및 반환
    return jwtService.createJWT(member.getAccount(), member.getEmail());
  }

  public Member getMemberByAccount(String account) {
    Member member = memberDao.selectByAccount(account);
    return member;
  }

  public Member getMemberByMid(Long mid) {
    Member member = memberDao.selectByMid(mid);
    return member;
  }

  // 이메일이랑 패스워드만 받아서 해도 되지않나...
  public Member update(Member member) {
    Member dbmember = memberDao.selectByAccount(member.getAccount());
    if (dbmember == null) {
      return null;
    } else {
      if (StringUtils.hasText(member.getPwd())) {
        dbmember.setPwd(member.getPwd());
      }
      if (StringUtils.hasText(member.getEmail())) {
        dbmember.setEmail(member.getEmail());
      }
    }
    memberDao.update(dbmember);
    dbmember = memberDao.selectByAccount(member.getAccount());
    return dbmember;

  }

  public enum RemoveResult {
    SUCCESS,
    FAIL
  }

  public RemoveResult deleteByAccount(String account) {
    int rows = memberDao.deleteByAccount(account);
    if (rows == 0) {
      return RemoveResult.FAIL;
    } else {
      return RemoveResult.SUCCESS;
    }
  }

  public RemoveResult deleteByMid(Long mid) {
    int rows = memberDao.deleteByMid(mid);
    if (rows == 0) {
      return RemoveResult.FAIL;
    } else {
      return RemoveResult.SUCCESS;
    }
  }

}
