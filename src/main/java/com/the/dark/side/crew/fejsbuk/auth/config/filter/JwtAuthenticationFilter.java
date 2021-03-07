package com.the.dark.side.crew.fejsbuk.auth.config.filter;

import com.auth0.jwt.JWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import static com.the.dark.side.crew.fejsbuk.auth.jwt.JwtUtil.JWT_ALGORITHM;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String JWT_HEADER_VALUE_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) throws IOException, ServletException {
        Optional.of(httpRequest)
                .map(request -> request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(header -> header.startsWith(JWT_HEADER_VALUE_PREFIX))
                .map(header -> header.substring(JWT_HEADER_VALUE_PREFIX.length()))
                .map(this::getUsernamePasswordAuthenticationToken)
                .ifPresent(token -> SecurityContextHolder.getContext().setAuthentication(token));
        chain.doFilter(httpRequest, httpResponse);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String jwt) {
        UsernamePasswordAuthenticationToken token = null;
        try {
            String userLogin = JWT.require(JWT_ALGORITHM)
                    .build()
                    .verify(jwt)
                    .getSubject();
            token = new UsernamePasswordAuthenticationToken(userLogin, null, Collections.emptyList());
        } catch (Exception exception) {
            log.debug("Invalid Access token.");
        }
        return token;
    }
}
