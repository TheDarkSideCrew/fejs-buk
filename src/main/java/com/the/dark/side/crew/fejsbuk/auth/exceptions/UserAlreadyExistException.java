package com.the.dark.side.crew.fejsbuk.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistException extends ResponseStatusException {

    public UserAlreadyExistException(String login) {
        super(HttpStatus.CONFLICT, "User '" + login + "' already exist.");
    }
}
