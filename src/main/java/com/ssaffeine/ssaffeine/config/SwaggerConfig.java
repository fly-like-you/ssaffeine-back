package com.ssaffeine.ssaffeine.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration    // 스프링 실행시 설정파일 읽어드리기 위한 어노테이션
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        String ipAddress = "localhost"; // 기본값

        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress(); // 시스템의 IP 주소 가져오기
        } catch (UnknownHostException e) {
            e.printStackTrace(); // 예외 처리
        }
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
                .addServersItem(new Server().url("http://" + ipAddress + ":8080"));

    }

    private Info apiInfo() {
        return new Info()
                .title("싸페인 API 문서")
                .description("주문, 설문, 유저에 관한 API를 설명하는 문서입니다.")
                .version("1.0.0");
    }
}