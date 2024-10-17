package com.ssaffeine.ssaffeine.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ssaffeine.ssaffeine.exception.UserException;
import com.ssaffeine.ssaffeine.exception.errorcode.ErrorCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleValidationNotAppropriate(UserException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(e.getStatusCode(), e.getMessage());
        problemDetail.setTitle("User Request Failed");

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    // 유효성 검사 실패 시 처리할 예외 핸들러
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, fieldErrors.get(0).getDefaultMessage());

        problemDetail.setProperty("error", fieldErrors.get(0).getField());

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        problemDetail.setProperty("errors", errors);

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    // HttpMessageNotReadableException 처리

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException invalidFormatException) {
            String fieldName = invalidFormatException.getPath().get(0).getFieldName();
            String invalidValue = invalidFormatException.getValue().toString();
            String expectedType = invalidFormatException.getTargetType().getSimpleName();

            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                    String.format("'%s' is not a valid value for field '%s'. Expected type: %s",
                            invalidValue, fieldName, expectedType));
            problemDetail.setTitle("Invalid Field Value");
            problemDetail.setProperty("error", fieldName);
            return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Malformed JSON request");
        problemDetail.setTitle("Request Parsing Failed");

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }



}
