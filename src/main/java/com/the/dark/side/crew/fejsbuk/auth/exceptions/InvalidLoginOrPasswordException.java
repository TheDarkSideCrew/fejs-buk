package com.the.dark.side.crew.fejsbuk.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidLoginOrPasswordException extends ResponseStatusException {

    public InvalidLoginOrPasswordException() {
        super(HttpStatus.UNAUTHORIZED, "Invalid login or password.");
    }
}
