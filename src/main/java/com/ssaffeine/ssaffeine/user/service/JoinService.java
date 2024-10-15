package com.ssaffeine.ssaffeine.user.service;


import com.ssaffeine.ssaffeine.user.domain.User;
import com.ssaffeine.ssaffeine.user.domain.UserRole;
import com.ssaffeine.ssaffeine.user.dto.JoinDTO;
import com.ssaffeine.ssaffeine.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO) {
        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        String loginId = joinDTO.getLoginId();

        // 사용자 이름 중복 체크
        Boolean exists = userRepository.existsByLoginId(password);
        if (exists) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        // User 엔티티 생성
        User user = new User();
        user.setLoginId(loginId);
        user.setUsername(username);
        user.setStudentNumber(joinDTO.getStudentNumber());
        user.setRegion(joinDTO.getRegion());  // 지역 설정 (Region Enum or String)
        user.setGroup(joinDTO.getGroup());  // 반 설정
        user.setPassword(bCryptPasswordEncoder.encode(password));  // 비밀번호 해싱

        // 기본 유저 역할 설정 (0: 일반 사용자, 1: 관리자)
        user.setRole(UserRole.ROLE_USER);
        log.info("user={}", user);
        // User 엔티티 저장
        userRepository.save(user);
    }

}