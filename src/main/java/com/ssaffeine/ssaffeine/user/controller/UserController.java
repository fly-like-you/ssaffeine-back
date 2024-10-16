package com.ssaffeine.ssaffeine.user.controller;

import com.ssaffeine.ssaffeine.user.controller.docs.UserControllerDocs;
import com.ssaffeine.ssaffeine.user.dto.request.UserRequestDto;
import com.ssaffeine.ssaffeine.user.dto.response.UserResponseDto;
import com.ssaffeine.ssaffeine.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
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

@RestController
public class UserController implements UserControllerDocs {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResponseDto> signUp(UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(userRequestDto));
    }

    @Override
    public ResponseEntity<UserResponseDto> getUserById(UUID userId) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUser(UUID userId, UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID userId) {
        return null;
    }
}
