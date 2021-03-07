package com.the.dark.side.crew.fejsbuk.auth.service;

import com.the.dark.side.crew.fejsbuk.auth.domain.dto.JwtResponse;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.LoginRequest;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.UserDto;

public interface AuthService {

    JwtResponse login(LoginRequest loginRequest);

    UserDto addUser(UserDto userDto);
}
