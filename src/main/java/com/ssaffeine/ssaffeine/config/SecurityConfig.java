package com.ssaffeine.ssaffeine.config;


import com.ssaffeine.ssaffeine.jwt.JWTUtil;
import com.ssaffeine.ssaffeine.jwt.JwtFilter;
import com.ssaffeine.ssaffeine.jwt.LoginFilter;
import com.ssaffeine.ssaffeine.security.CustomAuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private static final String[] PERMIT_URL_ARRAY = {
            /* swagger v3 */
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/swagger-resources/**",
            "/swagger-ui/index.css",
            "/swagger-ui/swagger-ui-bundle.js"
    };

    @Autowired
    public SecurityConfig(CustomAuthenticationEntryPoint authenticationEntryPoint, AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationEntryPoint = authenticationEntryPoint;
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
        // CORS 설정
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // 허용할 Origin
            configuration.setAllowedMethods(Collections.singletonList("*")); // 모든 HTTP 메서드 허용
            configuration.setAllowedHeaders(Collections.singletonList("*")); // 모든 헤더 허용
            configuration.setAllowCredentials(true); // 자격 증명 허용
            configuration.setMaxAge(3600L); // Preflight 요청 캐시 시간
            configuration.setExposedHeaders(Collections.singletonList("Authorization")); // 노출할 헤더 설정
            return configuration;
        }));

        // csrf disable
        // 세션방식에서는 세션이 고정이기때문에 고정해줘야함
        // jwt는 세션이 stateless하기 때문에 disable해줘도댐
        http
                .csrf((auth) -> auth.disable());
        http
                .formLogin((auth) -> auth.disable());
        http
                .httpBasic((auth) -> auth.disable());
        http
                .addFilterBefore(
                        new JwtFilter(authenticationEntryPoint, jwtUtil), LoginFilter.class
                );


        LoginFilter loginFilter = new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, authenticationEntryPoint);
        loginFilter.setUsernameParameter("loginId");
        log.info(loginFilter.getUsernameParameter());
        log.info(loginFilter.getPasswordParameter());
        http
                .addFilterAt(
                        loginFilter,
                        UsernamePasswordAuthenticationFilter.class
                );
        http
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                );
        // 세션 설정
        http
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 경로별로 인가 작업 특정 경로에 대해서 권한을 가져야하는 지 등을 설정
//        http
//                .authorizeHttpRequests((auth) -> auth
//                                .requestMatchers("/api/users/login", "/", "/api/users/signup", "/swagger-ui/**").permitAll()  // 모든 권한 허용
//                                .requestMatchers("/admin").hasRole("ADMIN")              // admin만 접근가능
//                        .anyRequest().authenticated()                              // 나머지는 로그인한 사용자만 접근 가능
//                );

//            http
//                    .authorizeHttpRequests((auth) -> auth
//                            .requestMatchers("/api/users/login", "/", "/api/users/signup").permitAll()  // 로그인 및 회원가입 허용
//                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()  // Swagger 관련 경로 허용
//                            .requestMatchers("/admin").hasAuthority("ADMIN")  // admin 권한 필요
//                            .anyRequest().authenticated()  // 나머지 경로는 인증 필요
//                    );

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(PERMIT_URL_ARRAY).permitAll()
                        // 로그인, 회원가입 경로 허용
                        .requestMatchers("/api/users/login", "/", "/api/users/signup").permitAll()
                        // Swagger 관련 경로 허용 (정적 리소스 포함)

                        // 그 외 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                );

        return http.build();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**");  // Swagger UI와 관련된 정적 리소스를 인증 없이 허용
//    }
}
