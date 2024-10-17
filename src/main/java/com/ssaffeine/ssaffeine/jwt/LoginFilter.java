package com.ssaffeine.ssaffeine.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssaffeine.ssaffeine.exception.CustomAuthenticationException;
import com.ssaffeine.ssaffeine.exception.errorcode.ErrorCode;
import com.ssaffeine.ssaffeine.security.CustomAuthenticationEntryPoint;
import com.ssaffeine.ssaffeine.user.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.lang.model.type.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
                       CustomAuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.authenticationEntryPoint = authenticationEntryPoint;
        setFilterProcessesUrl("/api/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        log.info("username = {}, password = {}", username, password);

        if (username == null || password == null) {
            handleAuthenticationFailure(request, response, new CustomAuthenticationException("아이디 또는 비밀번호가 없습니다."));
            return null;
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            // 인증 시도
            return authenticationManager.authenticate(authToken);
        } catch (AuthenticationException ex) {
            // 인증 실패 처리
            handleAuthenticationFailure(request, response, new CustomAuthenticationException("인증에 실패하였습니다."));
            return null;
        }

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        // role 뽑아오기
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String token = jwtUtil.createJwt(customUserDetails, auth,60 * 60 * 60 * 10L);
        response.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
    }

    private void handleAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                             AuthenticationException ex) {
        try {
            SecurityContextHolder.clearContext();  // 인증 정보 제거
            authenticationEntryPoint.commence(request, response, ex);  // 예외 처리
        } catch (IOException e) {

        }
    }
}
