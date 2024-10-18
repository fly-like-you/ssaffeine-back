package com.ssaffeine.ssaffeine.jwt;

import com.ssaffeine.ssaffeine.exception.CustomAuthenticationException;
import com.ssaffeine.ssaffeine.security.CustomAuthenticationEntryPoint;
import com.ssaffeine.ssaffeine.user.domain.User;
import com.ssaffeine.ssaffeine.user.domain.UserRole;
import com.ssaffeine.ssaffeine.user.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final JWTUtil jwtUtil;

    public JwtFilter(CustomAuthenticationEntryPoint authenticationEntryPoint, JWTUtil jwtUtil) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token = "";

        try {
            if (authorization == null || !authorization.startsWith("Bearer")) {
                throw new CustomAuthenticationException("JWT 토큰이 없습니다.");
            }

            // Bearer 제거
            token = authorization.split(" ")[1];

            // 토큰 만료 여부 확인
            if (jwtUtil.isExpired(token)) {
                throw new CustomAuthenticationException("JWT 토큰이 만료되었습니다.");
            }

            // 사용자 정보 추출
            String username = jwtUtil.getUsername(token);
            UserRole role = jwtUtil.getRole(token);
            String loginId = jwtUtil.getLoginId(token);
            UUID uuid = UUID.fromString(jwtUtil.getUuid(token));
            Integer semester = Integer.parseInt(jwtUtil.getSemester(token));

            User user = User.builder()
                    .uuid(uuid)
                    .loginId(loginId)
                    .role(role)
                    .username(username)
                    .semester(semester)
                    .region(jwtUtil.getRegion(token))
                    .group(jwtUtil.getGroup(token))
                    .build();

            // 사용자 인증 토큰 생성
            CustomUserDetails details = new CustomUserDetails(user);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    details, null, details.getAuthorities());

            // 사용자 인증 정보 설정
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (AuthenticationException ex) {
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, ex);
            return; // 예외 처리 후 필터 체인 중단
        }

        // 필터 체인 진행
        filterChain.doFilter(request, response);
    }
    // 필요한 경우 특정 경로에서 필터를 무시하도록 설정
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // 필터를 적용하지 않을 경로 리스트
        List<String> excludedPaths = Arrays.asList(
                "/api/users/signup",            // 회원가입 경로
                "/api/users/login",             // 로그인 경로
                "/swagger-ui/",       // Swagger UI 메인 페이지
                "/v3/api-docs"               // Swagger API 문서 경로
        );

        // 현재 요청 URI
        String requestURI = request.getRequestURI();

        // 요청 URI가 필터를 적용하지 않을 경로에 포함되는지 확인
        // `/**` 경로 패턴 처리를 위해 `startsWith` 사용
        return excludedPaths.stream().anyMatch(requestURI::startsWith);
    }



}
