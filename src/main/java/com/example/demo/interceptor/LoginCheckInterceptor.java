package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtService jwtService;

    // 전처리
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // preflight request 로 요청한 것은 통과
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        // 1. handler가 HandlerMethod 타입인지 먼저 확인합니다.
        if (!(handler instanceof HandlerMethod)) {
            // 2. 타입이 다르다면(정적 리소스 요청), 인터셉터 로직을 실행하지 않고 통과시킵니다.
            return true;
        }

        // 1)요청 매핑 메소드에 @Login이 붙어있는지 확인
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Login login = handlerMethod.getMethodAnnotation(Login.class);

        if (login != null) {
            // @Login이 붙어 있을 경우
            // 2) JWT가 있는지 확인
            String jwt = null;

            // 2-1) 요청 헤더에 실려왔는지 확인
            String authorization = request.getHeader("Authorization");
            if (authorization != null) {
                // Authorization: Bearer xxxxxx
                if (!authorization.substring(7).equals("")) {
                    jwt = authorization.substring(7);
                }
            }

            // 2-2) 쿼리 스트링 형태로 실려왔는지 확인
            String jwtParam = request.getParameter("jwt");
            if (jwtParam != null) {
                jwt = jwtParam;
            }

            if (jwt == null) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "JWT가 없습니다.");
                return false;
            } else {
                if (jwtService.validateJwt(jwt)) {
                    return true;
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "JWT가 유효하지 않습니다.");
                    return false;
                }
            }
        } else {
            // @Login이 붙어 있지 않을 경우
            return true;
        }

    }

}