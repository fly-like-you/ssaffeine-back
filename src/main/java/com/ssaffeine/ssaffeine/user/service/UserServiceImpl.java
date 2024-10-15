package com.ssaffeine.ssaffeine.user.service;

import com.ssaffeine.ssaffeine.exception.UserNotFoundException;
import com.ssaffeine.ssaffeine.user.domain.User;
import com.ssaffeine.ssaffeine.user.dto.request.UserRequestDto;
import com.ssaffeine.ssaffeine.user.dto.response.UserResponseDto;
import com.ssaffeine.ssaffeine.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto signUp(UserRequestDto userRequestDto) {
        String loginId = userRequestDto.getLoginId();

        // 사용자 이름 중복 체크
        Boolean exists = userRepository.existsByLoginId(loginId);
        if (exists) throw new IllegalArgumentException("이미 존재하는 사용자입니다.");

        // User 엔티티 생성
        User userInfo = userMapper.toEntity(userRequestDto);
        log.info("user={}", userInfo);

        User user = userRepository.save(userInfo);
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto getUserById(UUID userId) {
        // 주어진 ID로 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.BAD_REQUEST, "User not found with id: " + userId));

        // 조회된 사용자 정보를 UserResponseDto로 변환하여 반환
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateUser(UUID userId, UserRequestDto userRequestDto) {
        // 기존 사용자 조회
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.BAD_REQUEST, "User not found with id: " + userId));

        // 사용자 정보 업데이트
        existingUser.setUsername(userRequestDto.getUsername());
        existingUser.setRegion(userRequestDto.getRegion());
        existingUser.setGroup(userRequestDto.getGroup());
        existingUser.setPassword(userRequestDto.getPassword()); // 해싱 적용 필요

        // 업데이트된 사용자 저장
        User updatedUser = userRepository.save(existingUser);

        // 수정된 사용자 정보를 UserResponseDto로 반환
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        // 사용자 존재 여부 확인 후 삭제
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.BAD_REQUEST, "User not found with id: " + userId));

        userRepository.delete(user);
    }

}
