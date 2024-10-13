package com.ssaffeine.ssaffeine.user.service;


import com.ssaffeine.ssaffeine.user.domain.User;
import com.ssaffeine.ssaffeine.user.dto.CustomUserDetails;
import com.ssaffeine.ssaffeine.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자의 아이디로 유저 찾기
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User userData = userRepository.findByLoginId(loginId);

        // 검증 실행
        if (userData != null) {
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
