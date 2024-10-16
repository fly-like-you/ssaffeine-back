package com.ssaffeine.ssaffeine.user.service;

import com.ssaffeine.ssaffeine.exception.UserException;
import com.ssaffeine.ssaffeine.exception.errorcode.ErrorCode;
import com.ssaffeine.ssaffeine.user.domain.User;
import com.ssaffeine.ssaffeine.user.dto.request.UserRequestDto;
import com.ssaffeine.ssaffeine.user.dto.request.UserUpdateRequestDTO;
import com.ssaffeine.ssaffeine.user.dto.response.UserResponseDto;
import com.ssaffeine.ssaffeine.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserResponseDto signUp(UserRequestDto userRequestDto) {
        String loginId = userRequestDto.getLoginId();

        // 사용자 이름 중복 체크
        Boolean exists = userRepository.existsByLoginId(loginId);
        if (exists) throw new UserException(HttpStatus.BAD_REQUEST, ErrorCode.DUPLICATE_USER, loginId + " already exists");

        // User 엔티티 생성
        User userInfo = userMapper.toEntity(userRequestDto);
        log.info("created user, user={}", userInfo);

        User user = userRepository.save(userInfo);
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto getUserById(UUID userId) {
        // 주어진 ID로 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(HttpStatus.BAD_REQUEST, ErrorCode.DUPLICATE_USER, "User not found with id: " + userId));

        // 조회된 사용자 정보를 UserResponseDto로 변환하여 반환
        log.info("selected user, user={}", user);
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateUser(UUID userId, UserUpdateRequestDTO updateRequestDTO) {
        // 기존 사용자 조회
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(HttpStatus.BAD_REQUEST, ErrorCode.USER_NOT_FOUND , "User not found with id: " + userId));
        // 유저 정보 조회
        log.info("updated user, before: user={}", existingUser);

// 입력된 평문 비밀번호를 DB에 저장된 해시된 비밀번호와 비교
        if (!bCryptPasswordEncoder.matches(updateRequestDTO.getPassword(), existingUser.getPassword())) {
            throw new UserException(HttpStatus.BAD_REQUEST, ErrorCode.USER_VALIDATION_PASSWORD_ERROR, "비밀번호를 확인해주세요");
        }


        // 업데이트된 사용자 저장
        userMapper.update(existingUser, updateRequestDTO);
        User updatedUser = userRepository.save(existingUser);

        log.info("updated user, after: user={}", updatedUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        // 사용자 존재 여부 확인 후 삭제
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(HttpStatus.BAD_REQUEST, ErrorCode.USER_NOT_FOUND, "User not found with id: " + userId));

        userRepository.delete(user);
        log.info("user delete successful");
    }

}
