package com.ssaffeine.ssaffeine.exception;


import com.ssaffeine.ssaffeine.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class UserException extends ResponseStatusException {

    private final ErrorCode errorCode;

    public UserException(HttpStatusCode status, ErrorCode errorCode, String reason) {
        super(status, reason);
        this.errorCode = errorCode;
    }
}
