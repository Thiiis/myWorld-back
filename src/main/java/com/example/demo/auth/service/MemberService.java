package com.example.demo.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.auth.dao.MemberDao;
import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.dto.Member;
import com.example.demo.auth.dto.SignupRequest;
import com.example.demo.auth.dto.UpdateRequest;
import com.example.demo.profile.dao.ProfileDao;
import com.example.demo.profile.dto.Profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
  private final MemberDao memberDao;
  private final ProfileDao profileDao;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder; // SecurityConfig에 등록한 Bean을 주입받음
  // private final JwtService jwtService;

  @Transactional
  public Member signup(SignupRequest dto) {
    if (!dto.getPwd().equals(dto.getPwdConfirm())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
    int count = memberDao.countByAccount(dto.getAccount());
    if (count > 0) {
      // 이미 아이디가 존재하면 예외를 발생시킵니다.
      throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
    } else {
      String encodedPassword = passwordEncoder.encode(dto.getPwd());
      Member member = new Member(dto.getAccount(), dto.getEmail(), encodedPassword);
      memberDao.insert(member);
      // 2. member.getId()에 유효한 값이 들어왔는지 확인!
      // (디버깅으로 member 객체의 id 값을 꼭 확인해보세요)
      if (member.getMid() == 0) {
        // 이 경우라면 위의 Mapper.xml 설정이 잘못된 것입니다.
        throw new RuntimeException("멤버 ID를 가져오지 못했습니다.");
      }

      // 3. 가져온 member.getId()로 Profile 생성
      Profile profile = new Profile(member.getMid(), dto.getNickname(), dto.getBirthdate());
      profileDao.insert(profile);
      return member;
    }
  }

  public String login(LoginRequest dto) {
    // 1. 회원 아이디 조회
    Member member = memberDao.selectByAccount(dto.getAccount());
    // 2. 없으면 던지기
    if (member == null) {
      throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
    }
    // 3. 비밀번호 비교
    if (!passwordEncoder.matches(dto.getPwd(), member.getPwd())) {
      // 비밀번호가 일치하지 않으면 예외 발생
      throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
    }
    // 4. 로그인 성공: JWT 토큰 생성 및 반환
    return jwtService.createJWT(member.getAccount(), member.getEmail());
  }

// @Transactional
// public Member update(UpdateRequest dto) {
//     // 이메일과 비밀번호를 수정하려면, 비밀번호가 빈 문자열로 넘어오는지 체크
//     String email = dto.getEmail();
//     String pwd = dto.getPwd();
//     // Long mid = dto.getMid();

//     // 이메일만 수정할 경우 비밀번호를 null로 보내지 않도록 확인
//     if (email != null && email.isEmpty()) {
//         throw new IllegalArgumentException("이메일을 입력해주세요.");
//     }

//     // 비밀번호가 null일 경우, 비밀번호를 업데이트하지 않도록 처리
//     if (pwd != null && !pwd.isEmpty()) {
//         pwd = passwordEncoder.encode(pwd); // 비밀번호 암호화
//     }


// }


  public int deleteByMid(Long mid) {
    int rows = memberDao.deleteByMid(mid);
    return rows;
  }

}
