package com.ssaffeine.ssaffeine.jwt;

import com.ssaffeine.ssaffeine.user.domain.User;
import com.ssaffeine.ssaffeine.user.domain.UserRole;
import com.ssaffeine.ssaffeine.user.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtFilter extends OncePerRequestFilter { // 요청에 대해서 한번만 실행하는 필터

    private final JWTUtil jwtUtil;

    public JwtFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 토큰 검증용
        // request에서 Authorization 헤더를 찾기
        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 검증
        // 검증 실패
        if (authorization == null || !authorization.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Bearer 제거
        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtUtil.getUsername(token);
        UserRole role = jwtUtil.getRole(token);
        String loginId = jwtUtil.getLoginId(token);
        UUID userId = UUID.fromString(jwtUtil.getUserId(token));
        String studentNumber = jwtUtil.getStudentNumber(token);

        User user = User.builder()
                .userId(userId)
                .loginId(loginId)
                .role(role)
                .username(username)
                .studentNumber(studentNumber)
                .region(jwtUtil.getRegion(token))
                .group(jwtUtil.getGroup(token))
                .build();

            /*
    const user = {
          region: '부울경', 숫자로 지금 받게 하고 있음 -> 글자로
          group: 4,
          name: '권선',
          role: 'ROLE_ADMIN',
          // uuid나 학번 추가
          accessToken: 'someAccessToken', // Add this line
        };
     */

        // 스프링 시큐리티 인증 토큰 생성
        CustomUserDetails details = new CustomUserDetails(user);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                details, null, details.getAuthorities());

        // 세션에 사용자 토큰 등록
        log.info("사용자를 세션에 등록");
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);

    }
}
