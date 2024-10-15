package com.ssaffeine.ssaffeine.exception;

import java.net.URI;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(HttpStatusCode status) {
        super(status);
    }

    public UserNotFoundException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public UserNotFoundException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public UserNotFoundException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public UserNotFoundException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode,
                                 Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
