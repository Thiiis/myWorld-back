package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.auth.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

// 관리 빈이 아니라 서비스 컨트롤러가 붙지 않고 컴포넌트가 붙음
@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    // request 모든 요청에 대한 정보 받는거
    // response 모든 응답에 대한 정보 보내는거
    @Override
    // 전처리
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("전처리 실행");

        // preflight request 로 요청한 것은 통과
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        // 1) 요청 매핑 매소드에 @Login이 붙어있는지 확인
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Login login = handlerMethod.getMethodAnnotation(Login.class);
        if (login != null) {
            // @Login 붙어 있을 경우, 전처리 할 게 있다.
            // 2) JWT가 있는지 확인
            String jwt = null;
            String authorization = request.getHeader("Authorization");
            // 2-1) 요청 헤더에 실려왔는지 확인
            if (authorization != null) {
                // Authorization: Bearer XXXXXXXXXX
                if (!authorization.substring(7).equals("")) {
                    jwt = authorization.substring(7);
                }
            }
            // 2-2) 쿼리스트링 형태로 실려왔는지 확인, 요청헤더에 안 실려왔을 때 한 번 더 조사하는거임
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
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "유효하지 않습니다.");
                    return false;
                }
            }
        } else {
            // @Login이 붙어 있지 않을 경우
            return true;
        }

    }
}
