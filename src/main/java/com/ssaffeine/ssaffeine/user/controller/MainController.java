package com.ssaffeine.ssaffeine.user.controller;

import com.ssaffeine.ssaffeine.user.dto.CustomUserDetails;
import java.util.Iterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {
    @GetMapping("/")
    public String mainP(@AuthenticationPrincipal CustomUserDetails userDetails) {

        GrantedAuthority next = userDetails.getAuthorities().iterator().next();

        log.info("사용자 user={}", userDetails);

        return "Main Controller ";
    }
}
