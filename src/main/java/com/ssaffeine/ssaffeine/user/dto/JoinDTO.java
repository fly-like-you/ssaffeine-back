package com.ssaffeine.ssaffeine.user.dto;

import com.ssaffeine.ssaffeine.user.domain.Region;
import lombok.Getter;
import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JoinDTO {

    @NotBlank(message = "로그인 ID는 필수 항목입니다.")
    @Size(min = 4, max = 20, message = "로그인 ID는 4자에서 20자 사이여야 합니다.")
    private String loginId;

    @NotBlank(message = "유저 이름은 필수 항목입니다.")
    @Size(max = 20, message = "유저 이름은 최대 20자까지 가능합니다.")
    private String username;

    @NotBlank(message = "학번은 필수 항목입니다.")
    @Size(max = 10, message = "학번은 최대 10자까지 가능합니다.")
    private String studentNumber;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자에서 20자 사이여야 합니다.")
    private String password;

    @NotBlank(message = "지역은 필수 항목입니다.")
    private Region region;

    private Integer group;  // 선택 사항으로, 반 정보가 없을 수도 있음

}
