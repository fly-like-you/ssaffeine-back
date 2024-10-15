package com.ssaffeine.ssaffeine.user.service;

import com.ssaffeine.ssaffeine.user.dto.request.UserRequestDto;
import com.ssaffeine.ssaffeine.user.dto.response.UserResponseDto;
import java.util.UUID;

public interface UserService {

    UserResponseDto signUp(UserRequestDto userRequestDto);

    UserResponseDto getUserById(UUID userId);

    UserResponseDto updateUser(UUID userId, UserRequestDto userRequestDto);

    void deleteUser(UUID userId);
}
