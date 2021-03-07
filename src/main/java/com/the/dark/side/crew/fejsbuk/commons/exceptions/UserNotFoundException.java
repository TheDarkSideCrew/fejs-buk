package com.the.dark.side.crew.fejsbuk.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException(String login) {
        super(HttpStatus.NOT_FOUND, "User '" + login + "' not found.");
    }
}
