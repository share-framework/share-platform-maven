package org.andot.share.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TokenExpiredRuntimeException extends RuntimeException {
    public TokenExpiredRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
