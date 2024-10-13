package com.ssaffeine.ssaffeine.user.controller;

import com.ssaffeine.ssaffeine.user.dto.JoinDTO;
import com.ssaffeine.ssaffeine.user.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {
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
    private final JoinService joinService;
    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);

        return "ok";
    }
}
