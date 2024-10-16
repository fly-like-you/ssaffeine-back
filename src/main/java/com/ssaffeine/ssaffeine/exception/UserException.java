package com.ssaffeine.ssaffeine.exception;


import com.ssaffeine.ssaffeine.exception.errorcode.UserErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class UserException extends ResponseStatusException {

    private final UserErrorCode errorCode;

    public UserException(HttpStatusCode status, UserErrorCode errorCode, String reason) {
        super(status, reason);
        this.errorCode = errorCode;
    }
}
