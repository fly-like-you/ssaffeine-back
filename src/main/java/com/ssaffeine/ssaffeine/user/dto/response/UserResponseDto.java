package com.ssaffeine.ssaffeine.user.dto.response;

import com.ssaffeine.ssaffeine.user.domain.Region;
import com.ssaffeine.ssaffeine.user.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private UUID userId;
    private String loginId;
    private String username;
    private Integer semester;
    private Region region;
    private Integer group;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}