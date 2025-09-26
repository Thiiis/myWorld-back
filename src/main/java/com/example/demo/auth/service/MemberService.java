package com.example.demo.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.auth.dao.MemberDao;
import com.example.demo.auth.dto.MemberLoginRequest;
import com.example.demo.auth.dto.MemberReadResponse;
import com.example.demo.auth.dto.Member;
import com.example.demo.auth.dto.MemberSignupRequest;
import com.example.demo.auth.dto.MemberSignupResponse;
import com.example.demo.auth.dto.MemberUpdateRequest;
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

  // SecurityConfig에 등록한 Bean을 주입받음
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
    // 가져온 member.getId()로 Profile 생성
    Profile profile = new Profile(member.getMid(), dto.getNickname(), dto.getBirthdate());
    profileDao.insert(profile);

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
    return jwtService.createJWT(member.getAccount(), member.getEmail());
}
  // 프로필에서만 생년월일, 닉네임 보이게 할 것이냐...말 것이냐 그것이 문제로다...
  @Transactional(readOnly = true)
  public MemberReadResponse getMemberByMid(Long mid) {
      Member member = memberDao.selectByMid(mid);
      if (member == null) {
          throw new IllegalArgumentException("존재하지 않는 회원입니다.");
      }
      // Profile profile = profileDao.selectByMid(mid);
      // if(profile == null){
      //     throw new IllegalArgumentException("존재하지 않는 프로필입니다.");
      // }
      return new MemberReadResponse(
          member.getAccount(),
          member.getEmail()
          // ,profile.getNickname()
          // ,profile.getBirthdate()
      );
  }

  // 이메일이나 비밀번호 수정을 한꺼번에 할수있게 선택적으로 할건지
  // 이메일, 비밀번호 수정 따로따로 뺄건지 확인 >> 이러면 UpdateRequest 두개 들어가고 귀찮은데...
  @Transactional
  public void update(MemberUpdateRequest dto) {
    Long mid = dto.getMid();
    String email = dto.getEmail();
    String pwd = dto.getPwd();

    // 이메일만 수정할 경우 비밀번호를 null로 보내지 않도록 확인
    if (email == null || email.isEmpty()) {
        throw new IllegalArgumentException("이메일을 입력해주세요.");
    }
    // 비밀번호가 null이 아닌 경우에만 비밀번호 업데이트
    if (pwd != null && !pwd.isEmpty()) {
        pwd = passwordEncoder.encode(pwd); // 비밀번호 암호화
    }
    memberDao.update(mid,email,pwd);

  }


  public int deleteByMid(Long mid) {
    int rows = memberDao.deleteByMid(mid);
    return rows;
  }

}
