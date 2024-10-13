package com.ssaffeine.ssaffeine.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @GetMapping("/admin")
    public String adminP(@AuthenticationPrincipal Authentication authentication) {

        return "Admin Controller";
    }
}
