package com.ssaffeine.ssaffeine.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ProblemDetail;

@Builder
@AllArgsConstructor
@Getter
public class CustomProblemDetail<T> {
    private final ProblemDetail problemDetail;
    private final T errorCode; // 열거형


}
