package com.the.dark.side.crew.fejsbuk.auth.controller;

import com.the.dark.side.crew.fejsbuk.auth.domain.dto.JwtResponse;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.LoginRequest;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.UserDto;
import com.the.dark.side.crew.fejsbuk.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.the.dark.side.crew.fejsbuk.commons.Requests.AUTH_LOGIN;
import static com.the.dark.side.crew.fejsbuk.commons.Requests.AUTH_REFRESH;
import static com.the.dark.side.crew.fejsbuk.commons.Requests.AUTH_REGISTER;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(AUTH_LOGIN)
    public ResponseEntity<JwtResponse> login(LoginRequest loginRequest, HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(loginRequest, response));
    }

    @PostMapping(AUTH_REFRESH)
    public ResponseEntity<JwtResponse> refresh(HttpServletRequest request) {
        return ResponseEntity.ok(authService.refresh(request));
    }

    @PostMapping(AUTH_REGISTER)
    public ResponseEntity<UserDto> register(UserDto userDto) {
        return ResponseEntity.ok(authService.addUser(userDto));
    }
}
