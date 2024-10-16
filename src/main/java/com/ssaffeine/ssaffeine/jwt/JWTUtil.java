package com.ssaffeine.ssaffeine.jwt;

import com.ssaffeine.ssaffeine.user.domain.Region;
import com.ssaffeine.ssaffeine.user.domain.UserRole;
import com.ssaffeine.ssaffeine.user.dto.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JWTUtil {
    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {
        // 시크릿키를 객체변수로 암호화하는 코드
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                SIG.HS256.key().build().getAlgorithm());
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get("username", String.class);
    }

    public UserRole getRole(String token) {
        String role = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get("role", String.class);

        return UserRole.valueOf(role);
    }

    public String getUserId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get("userId", String.class);
    }

    public String getLoginId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get("loginId", String.class);
    }

    public Integer getSemester(String token) {
        return Integer.parseInt(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get("semester", String.class));
    }

    public Region getRegion(String token) {
        String region = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get("region", String.class);
        log.info("region={}", region);
        return Region.valueOf(region);
    }

    public Integer getGroup(String token) {
        return Integer.parseInt(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get("group", String.class));
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(CustomUserDetails customUserDetails, GrantedAuthority auth, Long expiredMs) {
        String loginId = customUserDetails.getLoginId();
        String username = customUserDetails.getUsername();
        String userId = customUserDetails.getUserId();
        Integer semester = customUserDetails.getSemester();
        String region = customUserDetails.getRegion().toString();
        String group = customUserDetails.getGroup() + "";

        String role = auth.getAuthority();
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .claim("userId", userId)
                .claim("loginId", loginId)
                .claim("semester", semester)
                .claim("region", region)
                .claim("group", group)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey) // 암호화
                .compact();
    }


}
