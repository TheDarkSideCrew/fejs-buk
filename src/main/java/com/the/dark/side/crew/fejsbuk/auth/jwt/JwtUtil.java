package com.the.dark.side.crew.fejsbuk.auth.jwt;

import com.the.dark.side.crew.fejsbuk.auth.domain.dto.JwtResponse;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.LoginRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public JwtResponse getAccessToken(LoginRequest request) {
        return null;
    }

    public String getRefreshToken(LoginRequest loginRequest) {
        return null;
    }
}
