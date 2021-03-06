package com.the.dark.side.crew.fejsbuk.auth.service;

import com.the.dark.side.crew.fejsbuk.auth.domain.dto.JwtResponse;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.LoginRequest;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    JwtResponse login(LoginRequest loginRequest, HttpServletResponse response);

    UserDto register(UserDto userDto);

    JwtResponse refresh(HttpServletRequest response);

    void logout(HttpServletResponse response);
}
