package com.the.dark.side.crew.fejsbuk.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RefreshTokenNotFoundException extends ResponseStatusException {

    public RefreshTokenNotFoundException() {
        super(HttpStatus.UNAUTHORIZED, "Refresh token not found.");
    }
}
