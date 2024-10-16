package com.ssaffeine.ssaffeine.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum UserErrorCode {
    /* ---------------- 사용자 회원 가입 관련 에러 코드 --------------- */
    USER_NOT_FOUND("4001", "사용자를 찾을 수 없습니다."),
    INVALID_USER_INPUT("4002", "잘못된 입력입니다."),
    DUPLICATE_USER("4003", "이미 존재하는 사용자입니다."),
    USER_ACCESS_DENIED("4004", "사용자 접근이 거부되었습니다."),
    USER_CREATION_FAILED("4005", "사용자 생성에 실패했습니다."),


    /* ---------------- 사용자 로그인 관련 에러 코드 --------------- */
    USER_LOGIN_FAILED("4006", "로그인에 실패하였습니다.");


    private final String code;
    private final String message;
}
