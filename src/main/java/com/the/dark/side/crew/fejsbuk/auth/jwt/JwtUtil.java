package com.the.dark.side.crew.fejsbuk.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.the.dark.side.crew.fejsbuk.auth.domain.enums.TokenType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    public static final long SECONDS_TO_MILLISECONDS_MULTIPLIER = 1000;
    public static final String TYPE = "type";

    public static Algorithm JWT_ALGORITHM;

    @Value("${jwt.access.token.expiration.seconds}")
    private long accessTokenExpirationSeconds;
    @Value("${jwt.secret}")
    private void initAlgorithm(String secret) {
        JWT_ALGORITHM = Algorithm.HMAC256(secret);
    }

    public String getJwtToken(String login, TokenType tokenType, long expirationSec) {
        Date issuedAt = new Date();
        return JWT.create()
                .withClaim(TYPE, tokenType.name())
                .withSubject(login)
                .withIssuedAt(issuedAt)
                .withExpiresAt(new Date(issuedAt.getTime() + expirationSec * SECONDS_TO_MILLISECONDS_MULTIPLIER))
                .sign(JWT_ALGORITHM);
    }

    public String getAccessTokenFromRefreshTokens(Cookie refreshToken, Cookie logoutRefreshToken) {
        try {
            DecodedJWT decodedRefreshToken = JWT.require(JWT_ALGORITHM)
                    .build()
                    .verify(refreshToken.getValue());
            DecodedJWT decodedLogoutRefreshToken = JWT.require(JWT_ALGORITHM)
                    .build()
                    .verify(logoutRefreshToken.getValue());
            if (tokenTypesValid(decodedRefreshToken, decodedLogoutRefreshToken)
                    && subjectValid(decodedRefreshToken, decodedLogoutRefreshToken)) {
                return getJwtToken(decodedRefreshToken.getSubject(), TokenType.ACCESS, accessTokenExpirationSeconds);
            }
        } catch (Exception exception) {
            log.debug("Refreshing access token failed.");
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refreshing access token failed.");
    }

    private boolean subjectValid(DecodedJWT decodedRefreshToken, DecodedJWT decodedLogoutRefreshToken) {
        return decodedRefreshToken.getSubject().equals(decodedLogoutRefreshToken.getSubject());
    }

    private boolean tokenTypesValid(DecodedJWT refreshToken, DecodedJWT logoutRefreshToken) {
        return TokenType.REFRESH.name().equals(refreshToken.getClaim(TYPE).asString()) &&
                TokenType.LOGOUT_REFRESH.name().equals(logoutRefreshToken.getClaim(TYPE).asString());
    }
}
