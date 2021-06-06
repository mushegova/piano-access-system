package org.piano.access.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidDataException extends RuntimeException {

    public InvalidDataException(Object cause) {
        super(String.format("Room or User is not valid: %s", cause));
    }

}
