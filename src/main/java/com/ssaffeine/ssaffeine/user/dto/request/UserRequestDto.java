package com.ssaffeine.ssaffeine.user.dto.request;

import com.ssaffeine.ssaffeine.user.domain.Region;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "로그인 ID는 필수 항목입니다.")
    @Size(min = 5, max = 50, message = "로그인 ID는 5자 이상, 50자 이하여야 합니다.")
    private String loginId;

    @Schema(description = "사용자의 한국어 이름", example = "홍길동")
    @NotBlank(message = "이름은 필수 항목입니다.")
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 입력 가능합니다.")
    private String username;

    @Schema(description = "사용자의 학기", example = "12")
    @NotNull(message = "학기는 필수 항목입니다.")
    @Min(value = 0, message = "학기는 0 이상의 정수여야 합니다.")
    private Integer semester;

    @Schema(description = "사용자의 지역", example = "E005")
    @NotNull(message = "지역은 필수 항목입니다.")
    private Region region;

    @Schema(description = "사용자의 반 번호", example = "2")
    @NotNull(message = "반 번호는 필수 항목입니다.")
    @Max(value = 20, message = "반 번호는 20 이하의 정수여야 합니다.")
    @Min(value = 0, message = "반 번호는 0 이상의 정수여야 합니다.")
    private Integer group;

    @Schema(description = "사용자의 비밀 번호", example = "password1234")
    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    // Getters and Setters
}
