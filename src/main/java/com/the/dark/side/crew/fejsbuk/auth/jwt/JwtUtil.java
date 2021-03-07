package com.the.dark.side.crew.fejsbuk.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.JwtResponse;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    public static final long SECONDS_TO_MILLISECONDS_MULTIPLIER = 1000;

    @Value("{jwt.secret}")
    private String secret;
    @Value("{jwt.access.token.duration.seconds}")
    private long accessTokenDurationSeconds;
    @Value("{jwt.refresh.token.duration.seconds}")
    private long refreshTokenDurationSeconds;

    public JwtResponse getAccessToken(LoginRequest request) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Date issuedAt = new Date();
        String accessToken = JWT.create()
                .withSubject(request.getLogin())
                .withIssuedAt(issuedAt)
                .withExpiresAt(new Date(issuedAt.getTime() + getAccessTokenDurationMilliseconds()))
                .sign(algorithm);
        return JwtResponse.of(accessToken);
    }

    public String getRefreshToken(LoginRequest request) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Date issuedAt = new Date();
        return JWT.create()
                .withSubject(request.getLogin())
                .withIssuedAt(issuedAt)
                .withExpiresAt(new Date(issuedAt.getTime() + getRefreshTokenDurationMilliseconds()))
                .sign(algorithm);
    }

    private long getAccessTokenDurationMilliseconds() {
        return accessTokenDurationSeconds * SECONDS_TO_MILLISECONDS_MULTIPLIER;
    }

    private long getRefreshTokenDurationMilliseconds() {
        return accessTokenDurationSeconds * SECONDS_TO_MILLISECONDS_MULTIPLIER;
    }
}
