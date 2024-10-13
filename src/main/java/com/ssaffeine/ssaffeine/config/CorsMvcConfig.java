package com.ssaffeine.ssaffeine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        // 모든 경로의 요청에 대해서 cors gjdyd
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000");
    }
}
