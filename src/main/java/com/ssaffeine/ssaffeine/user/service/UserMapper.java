package com.ssaffeine.ssaffeine.user.service;

import com.ssaffeine.ssaffeine.user.domain.User;
import com.ssaffeine.ssaffeine.user.domain.UserRole;
import com.ssaffeine.ssaffeine.user.dto.request.UserRequestDto;
import com.ssaffeine.ssaffeine.user.dto.request.UserUpdateRequestDTO;
import com.ssaffeine.ssaffeine.user.dto.response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserMapper(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // UserRequestDto를 User 엔티티로 변환하는 메서드
    public User toEntity(UserRequestDto userRequestDto) {
        return User.builder()
                .loginId(userRequestDto.getLoginId())
                .username(userRequestDto.getUsername())
                .semester(userRequestDto.getSemester())
                .region(userRequestDto.getRegion())
                .group(userRequestDto.getGroup())
                .password(bCryptPasswordEncoder.encode(userRequestDto.getPassword()))
                .role(UserRole.ROLE_USER)
                .build();
    }

    // User 엔티티를 UserResponseDto로 변환하는 메서드
    public UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .uuid(user.getUuid())
                .loginId(user.getLoginId())
                .username(user.getUsername())
                .semester(user.getSemester())
                .region(user.getRegion())
                .group(user.getGroup())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public void update(User existingUser, UserUpdateRequestDTO updateDTO) {
        // 사용자 정보 업데이트
        existingUser.setUsername(updateDTO.getUsername());
        existingUser.setSemester(updateDTO.getSemester());
        existingUser.setRegion(updateDTO.getRegion());
        existingUser.setGroup(updateDTO.getGroup());
        existingUser.setPassword(bCryptPasswordEncoder.encode(updateDTO.getChangedPassword()));
    }
}
