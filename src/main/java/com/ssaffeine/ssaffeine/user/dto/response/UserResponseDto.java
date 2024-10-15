package com.ssaffeine.ssaffeine.user.dto.response;

import com.ssaffeine.ssaffeine.user.domain.Region;
import com.ssaffeine.ssaffeine.user.domain.User;
import com.ssaffeine.ssaffeine.user.domain.UserRole;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private UUID userId;
    private String loginId;
    private String username;
    private String studentNumber;
    private Region region;
    private Integer group;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}