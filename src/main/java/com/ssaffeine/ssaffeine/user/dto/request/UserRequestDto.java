package com.ssaffeine.ssaffeine.user.dto.request;

import com.ssaffeine.ssaffeine.user.domain.Region;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
// 200
// 2001
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @Schema(description = "사용자의 로그인 ID", example = "user123")
    private String loginId;

    @Schema(description = "사용자의 한국어 이름", example = "홍길동")
    private String username;

    @Schema(description = "사용자의 학기", example = "12")
    private Integer semester;

    @Schema(description = "사용자의 지역", example = "E005")
    private Region region;

    @Schema(description = "사용자의 반 번호", example = "2")
    private Integer group;

    @Schema(description = "사용자의 비밀 번호", example = "password1234")
    private String password;

}