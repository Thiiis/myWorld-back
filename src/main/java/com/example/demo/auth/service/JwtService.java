package com.example.demo.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class JwtService {
    // 문자열 비밀키(노출이 되면 안됨)
    private String strKey = "com.mycompany.backendapi.secret.key";
    // 서명 및 암호화를 위한 비밀키(SecretKey)
    private SecretKey secretKey;
    // JWT의 유효 기간(단위: 밀리세컨) >> 짧게 가는게 좋음
    private long jwtDuration = 24*60*60*1000;// 1일

    // 생성자
    public JwtService() throws Exception{
        byte [] bytes = strKey.getBytes("UTF-8");
        secretKey = Keys.hmacShaKeyFor(bytes);
    }
    // JWT 생성 메소드
    public String createJWT(String account, String email){
        //JWT를 생성하는 빌더 얻기
        JwtBuilder jwtBuilder = Jwts.builder();

        //JWT에 포함할 Payload 추가, payload 자체가 싣는 짐
        jwtBuilder.subject(account);
        jwtBuilder.claim("email",email);

        //JWT 유효기간 설정
        jwtBuilder.expiration(new Date(new Date().getTime() + jwtDuration));
        
        //SecretKey 설정
        jwtBuilder.signWith(secretKey);

        //JWT 얻기
        String jwt = jwtBuilder.compact();

        return jwt;

    }
    // 유효성 검사
    public boolean validateJwt(String jwt){
        boolean result = false;
        try{

            // JWT를 해석하는 JwtParser 얻기
            JwtParserBuilder jwtParserBuilder = Jwts.parser();
            jwtParserBuilder.verifyWith(secretKey);
            JwtParser jwtParser = jwtParserBuilder.build();
            
            // JWT를 해석
            // SignedClaims 암호화되고 서명된 정보를 해석하겠따
            Jws<Claims> jws = jwtParser.parseSignedClaims(jwt);
            result = true;
        }catch(ExpiredJwtException e){
            log.info("기간이 만료된 토큰입니다.");
        }catch(io.jsonwebtoken.security.SecurityException e){
            // SecurityException만 쓰면 자바 유틸꺼 임포트됨
            log.info("잘못된 서명입니다.");
        }catch(Exception e){
            log.info("토큰을 해석할 수 없습니다.");
        }
        return result;

    }
    // jwt안 Claims(정보) 빼내기
    public Map<String,String> getClaims(String jwt){
        JwtParserBuilder jwtParserBuilder = Jwts.parser();
        jwtParserBuilder.verifyWith(secretKey);
        JwtParser jwtParser = jwtParserBuilder.build();
        
        Jws<Claims> jws = jwtParser.parseSignedClaims(jwt);
        Claims claims = jws.getPayload();

        Map<String,String> map = new HashMap<>();
        map.put("account",claims.getSubject());
        map.put("email",claims.get("email").toString());
        return map;
    }
}