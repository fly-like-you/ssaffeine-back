package com.ssaffeine.ssaffeine.advice;

import com.ssaffeine.ssaffeine.exception.ResourceNotFoundException;
import com.ssaffeine.ssaffeine.exception.UserException;
import com.ssaffeine.ssaffeine.exception.errorcode.UserErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * { "type": "about:blank", "title": "리소스 찾을 수 없음", "status": 404, "detail": "리소스를 찾을 수 없습니다: 2", "instance":
     * "https://example.com/errors/not-found" }
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
        // PrwoblemDetail을 생성하여 반환
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        problemDetail.setTitle("리소스 찾을 수 없음");
        problemDetail.setInstance(URI.create("https://example.com/errors/not-found")); // 관련 문서 링크 (선택 사항)
        return problemDetail;
    }

    @ExceptionHandler(UserException.class)
    public CustomProblemDetail<UserErrorCode> handleValidationNotAppropriate(UserException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(e.getStatusCode(), e.getMessage());
        problemDetail.setTitle("사용자 요청 오류");
        return CustomProblemDetail.<UserErrorCode>builder()
                .errorCode(e.getErrorCode())
                .problemDetail(problemDetail)
                .build();
    }
}
