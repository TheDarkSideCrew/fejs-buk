package com.the.dark.side.crew.fejsbuk.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.JwtResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    public static final long SECONDS_TO_MILLISECONDS_MULTIPLIER = 1000;
    public static final String REFRESH_TOKEN_TYPE = "refresh";

    public static Algorithm JWT_ALGORITHM;

    @Value("${jwt.access.token.duration.seconds}")
    private long accessTokenDurationSeconds;
    @Value("${jwt.refresh.token.duration.seconds}")
    private long refreshTokenDurationSeconds;

    @Value("${jwt.secret}")
    private void initAlgorithm(String secret) {
        JWT_ALGORITHM = Algorithm.HMAC256(secret);
    }

    public JwtResponse getAccessToken(String login) {
        Date issuedAt = new Date();
        String accessToken = JWT.create()
                .withSubject(login)
                .withIssuedAt(issuedAt)
                .withExpiresAt(new Date(issuedAt.getTime() + getAccessTokenDurationMilliseconds()))
                .sign(JWT_ALGORITHM);
        return new JwtResponse(accessToken);
    }

    public String getRefreshToken(String login) {
        Date issuedAt = new Date();
        return JWT.create()
                .withClaim("type", REFRESH_TOKEN_TYPE)
                .withSubject(login)
                .withIssuedAt(issuedAt)
                .withExpiresAt(new Date(issuedAt.getTime() + getRefreshTokenDurationMilliseconds()))
                .sign(JWT_ALGORITHM);
    }

    public JwtResponse getAccessTokenFromRefreshToken(String refreshToken) {
        try {
            DecodedJWT decodedJWT = JWT.require(JWT_ALGORITHM)
                    .build()
                    .verify(refreshToken);
            if (REFRESH_TOKEN_TYPE.equals(decodedJWT.getClaim("type").asString())) {
                return getAccessToken(decodedJWT.getSubject());
            }
        } catch (JWTVerificationException exception) {
            log.debug("Refresh token not valid");
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token not valid");
    }

    private long getAccessTokenDurationMilliseconds() {
        return accessTokenDurationSeconds * SECONDS_TO_MILLISECONDS_MULTIPLIER;
    }

    private long getRefreshTokenDurationMilliseconds() {
        return accessTokenDurationSeconds * SECONDS_TO_MILLISECONDS_MULTIPLIER;
    }
}
