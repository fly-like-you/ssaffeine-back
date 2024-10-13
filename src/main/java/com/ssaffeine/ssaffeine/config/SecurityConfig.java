package com.ssaffeine.ssaffeine.config;


import com.ssaffeine.ssaffeine.jwt.JWTUtil;
import com.ssaffeine.ssaffeine.jwt.JwtFilter;
import com.ssaffeine.ssaffeine.jwt.LoginFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Slf4j
// 스프링한테 Configuration으로 등록
@Configuration
@EnableWebSecurity // Security라는것을 알리기
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    @Autowired
    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    // 메소드들을 빈으로 등록하면서 시큐리티 설정을 진행 가능

    // 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();
                        // 허용할 포트 (3000)
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        // 허용할 메서드 (GET, POST, 등)
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                        return configuration;
                    }
                })));
        // csrf disable
        // 세션방식에서는 세션이 고정이기때문에 고정해줘야함
        // jwt는 세션이 stateless하기 때문에 disable해줘도댐
        http
                .csrf((auth) -> auth.disable());
        // form 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());
        // http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        // 경로별로 인가 작업 특정 경로에 대해서 권한을 가져야하는 지 등을 설정
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("login", "/", "join").permitAll()  // 모든 권한 허용
                        .requestMatchers("/admin").hasRole("ADMIN")              // admin만 접근가능
                        .anyRequest().authenticated()                              // 나머지는 로그인한 사용자만 접근 가능
                );

        http
                .addFilterBefore(
                        new JwtFilter(jwtUtil), LoginFilter.class
                );

        LoginFilter loginFilter = new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil);
        loginFilter.setUsernameParameter("loginId");
        log.info(loginFilter.getUsernameParameter());
        log.info(loginFilter.getPasswordParameter());



        http
                .addFilterAt(
                        loginFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        // 세션 설정
        // jwt에서는 세션을 statless 상태로 관리한다. 이걸 알려줘야함
        http
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }


}
