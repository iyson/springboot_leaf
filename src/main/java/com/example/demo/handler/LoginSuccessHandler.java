package com.example.demo.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
        // 로그인한 사용자의 ID 가져오기
        String userId = authentication.getName();
        log.info("로그인성공:", userId);
        
        // 2. 성공 후 이동할 페이지 설정
        setDefaultTargetUrl("/main");
        
        // 3. 부모 클래스의 로직 수행 (리다이렉트 처리 등)
        super.onAuthenticationSuccess(request, response, authentication);
    }
}