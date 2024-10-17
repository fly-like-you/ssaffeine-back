package com.ssaffeine.ssaffeine.user.controller;

import com.ssaffeine.ssaffeine.exception.UserException;
import com.ssaffeine.ssaffeine.exception.errorcode.ErrorCode;
import com.ssaffeine.ssaffeine.user.controller.docs.UserControllerDocs;
import com.ssaffeine.ssaffeine.user.domain.UserRole;
import com.ssaffeine.ssaffeine.user.dto.CustomUserDetails;
import com.ssaffeine.ssaffeine.user.dto.request.UserRequestDto;
import com.ssaffeine.ssaffeine.user.dto.request.UserUpdateRequestDTO;
import com.ssaffeine.ssaffeine.user.dto.response.UserResponseDto;
import com.ssaffeine.ssaffeine.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController implements UserControllerDocs {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(userRequestDto));
    }

    @Override
    @GetMapping("/{userId}")

    public ResponseEntity<UserResponseDto> getUserById(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable UUID userId
    ) {
        if (customUserDetails.getUserRole() == UserRole.ROLE_USER) {
            throw new UserException(HttpStatus.UNAUTHORIZED, ErrorCode.USER_ACCESS_DENIED, "권한 부족");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

    @Override
    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody UserUpdateRequestDTO userRequestDto) {

        UUID userId = UUID.fromString(customUserDetails.getUuid());
        log.info("user id: {}", userId);
        log.info("requestDto={}", userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, userRequestDto));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        UUID userId = UUID.fromString(customUserDetails.getUuid());
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
