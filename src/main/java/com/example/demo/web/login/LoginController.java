package com.example.demo.web.login;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.web.login.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
//@RequestMapping("/")
public class LoginController {

	private final AuthenticationManager authenticationManager;

    @PostMapping("/loginProc")
    public ResponseEntity<?> login(@RequestBody UserVo userVo, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 1. 인증 토큰 생성
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userVo.getUserId(), userVo.getPassword());

            log.info("loginProc userId::: " + userVo.getUserId());
            
            // 2. 실제 인증 시도 (CustomUserDetailsService 호출됨)
            Authentication authentication = authenticationManager.authenticate(token);

            // 3. 인증 성공 시 SecurityContext에 저장 (세션 방식일 경우)
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // 4. 세션 고정 보호 등을 위해 세션 생성
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            // 5. 응답 데이터 구성
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("userId", authentication.getName());
            
            return ResponseEntity.ok(result);

        } catch (AuthenticationException e) {
            // 인증 실패 시
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // 1. 현재 세션을 가져옴 (존재하지 않으면 null 반환)
        HttpSession session = request.getSession(false);

        if (session != null) {
            // 2. 세션 무효화 (세션 내 모든 데이터 삭제)
            session.invalidate();
            log.info("세션이 성공적으로 무효화되었습니다.");
        }

        // 3. SecurityContextHolder의 인증 정보 클리어
        // 현재 스레드에 남아있는 인증 정보를 명시적으로 삭제
        SecurityContextHolder.clearContext();

        // 4. 응답 구성
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "로그아웃 되었습니다.");

        return ResponseEntity.ok(result);
    }    

}
