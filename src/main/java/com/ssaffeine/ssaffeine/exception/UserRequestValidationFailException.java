package com.ssaffeine.ssaffeine.exception;


import java.net.URI;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class UserRequestValidationFailException extends ResponseStatusException {
    public UserRequestValidationFailException(HttpStatusCode status) {
        super(status);
    }

    public UserRequestValidationFailException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public UserRequestValidationFailException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public UserRequestValidationFailException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public UserRequestValidationFailException(HttpStatusCode status, String reason, Throwable cause,
                                              String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
