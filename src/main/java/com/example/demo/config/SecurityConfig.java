package com.example.demo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.handler.LoginFailureHandler;
import com.example.demo.handler.LoginSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	//오리진 설정은 반드시 http 까지 붙여야됨
	private String[] origin = {
		"http://localhost:8083", 
	    "http://localhost:8080", 
	    "http://localhost:4000"
	};
	private final LoginSuccessHandler loginSuccessHandler;
	private final LoginFailureHandler loginFailureHandler;
	private final UserDetailsService userDetailsService;
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //화면 api가 하나로 구성된 프로젝트
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/loginProc", "/js/**", "/css/**", "/assets/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/loginProc") // ★ 시큐리티가 가로채서 처리함
                .usernameParameter("userId")      // JS에서 보내는 key값
                .passwordParameter("password")
                .successHandler(loginSuccessHandler) // 성공 시 JSON 응답은 여기서!
                .failureHandler(loginFailureHandler) // 실패 시 JSON 응답은 여기서!
                .permitAll()
            );
        
        return http.build();
    }
    
    //front api 구성으로 이뤄진 프로젝트
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        // 1. 시큐리티 필터 체인 제일 앞단에서 CORS 설정을 적용합니다.
        .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            // 2. OPTIONS 메서드는 CORS Preflight를 위해 무조건 허용하는 것이 안전합니다.
            .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/api/loginProc", "/api/login", "/login", "/css/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form.disable())
        .logout(logout -> logout.logoutSuccessUrl("/login"));

    return http.build();
    }
    */

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        log.info("CORS origins: {}", Arrays.toString(origin));
        configuration.setAllowedOrigins(Arrays.asList(origin));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "state", "count"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}