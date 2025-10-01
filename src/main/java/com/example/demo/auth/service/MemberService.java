package com.example.demo.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.auth.dao.MemberDao;
import com.example.demo.auth.dto.Member;
import com.example.demo.auth.dto.MemberLoginRequest;
import com.example.demo.auth.dto.MemberLoginResponse;
import com.example.demo.auth.dto.MemberReadResponse;
import com.example.demo.auth.dto.MemberSignupRequest;
import com.example.demo.auth.dto.MemberSignupResponse;
import com.example.demo.auth.dto.MemberUpdateRequest;
import com.example.demo.profile.dao.ProfileDao;
import com.example.demo.profile.dto.Profile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
  private final MemberDao memberDao;
  private final ProfileDao profileDao;
  private final JwtService jwtService;

  private final PasswordEncoder passwordEncoder;

  @Transactional
  public MemberSignupResponse signup(MemberSignupRequest dto) {
    
    if (!dto.getPwd().equals(dto.getPwdConfirm())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
    if (memberDao.countByAccount(dto.getAccount()) > 0) {
      throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
    }
    // 패스워드 인코드해서 멤버 생성
    String encodedPassword = passwordEncoder.encode(dto.getPwd());
    Member member = new Member(dto.getAccount(), dto.getEmail(), encodedPassword);
    memberDao.insert(member);

    // member.getId()에 유효한 값이 들어왔는지 확인, Profile에 써야함
    if (member.getMid() == null || member.getMid() == 0) {
      // 해당 경우 나오면 Mapper.xml 확인
      throw new RuntimeException("멤버 ID를 가져오지 못했습니다.");
    }
    if(profileDao.countByNickname(dto.getNickname()) > 0){
    throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
    }
    Profile profile = new Profile(member.getMid(), dto.getNickname(), dto.getBirthdate());
    profileDao.insert(profile);
    // 가져온 member.getId()로 Profile 생성

    // DB 최신 업뎃 반영
      return new MemberSignupResponse(member.getAccount(), profile.getNickname(), member.getEmail(),profile.getBirthdate());
    }
  

@Transactional(readOnly = true)
public String login(MemberLoginRequest dto) {
    // 1. 회원 조회
    Member member = memberDao.selectByAccount(dto.getAccount());
    if (member == null) {
        throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
    }

    // 2. 비밀번호 검증
    if (!passwordEncoder.matches(dto.getPwd(), member.getPwd())) {
        throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
    }

    // 3. JWT 발급
    return jwtService.createJWT(member.getMid(), member.getAccount(), member.getEmail());
}

  @Transactional(readOnly = true)
  public MemberReadResponse getMember(String account) {
      Member member = memberDao.selectByAccount(account);
      
      if (member == null) {
          throw new IllegalArgumentException("존재하지 않는 회원입니다.");
      }
      return new MemberReadResponse(member.getMid(), member.getAccount(), member.getEmail());
  }

  
  public void updatePwd(@Valid MemberUpdateRequest dto) {
// 1. 새로운 비밀번호와 비밀번호 확인이 일치하는지 검사
        if (!dto.getNewPwd().equals(dto.getNewPwdConfirm())) {
            throw new IllegalArgumentException("새로운 비밀번호가 일치하지 않습니다.");
        }

        // 2. DB에서 현재 회원 정보 조회
        Member member = memberDao.selectByMid(dto.getMid())
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        // 3. 현재 비밀번호가 맞는지 확인 (가장 중요!)
        // DB에 저장된 암호화된 비밀번호와, 사용자가 입력한 현재 비밀번호를 비교
        if (!passwordEncoder.matches(dto.getCurrentPwd(), member.getPwd())) {
            throw new IllegalArgumentException("현재 비밀번호가 올바르지 않습니다.");
        }

        // 4. 새로운 비밀번호를 암호화
        String newHashedPassword = passwordEncoder.encode(dto.getNewPwd());

        // 5. DB에 암호화된 새 비밀번호로 업데이트
        memberDao.update(member.getMid(), newHashedPassword);
  }


  public int deleteByMid(Long mid) {
    int rows = memberDao.deleteByMid(mid);
    return rows;
  }

}
