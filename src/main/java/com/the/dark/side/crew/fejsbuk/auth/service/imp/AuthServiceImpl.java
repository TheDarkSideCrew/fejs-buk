package com.the.dark.side.crew.fejsbuk.auth.service.imp;

import com.the.dark.side.crew.fejsbuk.auth.domain.dto.JwtResponse;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.LoginRequest;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.UserDto;
import com.the.dark.side.crew.fejsbuk.auth.domain.entity.UserEntity;
import com.the.dark.side.crew.fejsbuk.auth.jwt.JwtUtil;
import com.the.dark.side.crew.fejsbuk.auth.mapper.UserMapper;
import com.the.dark.side.crew.fejsbuk.auth.service.AuthService;
import com.the.dark.side.crew.fejsbuk.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public JwtResponse login(LoginRequest loginRequest, HttpServletResponse httpResponse) {
        JwtResponse jwtResponse;
        UserEntity userEntity = userRepository.findByLogin(loginRequest.getLogin())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User '" + loginRequest.getLogin() + "' not found."));
        if (passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
            jwtResponse = jwtUtil.getAccessToken(loginRequest.getLogin());
            httpResponse.addCookie(getRefreshTokenCookie(loginRequest));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong login or password");
        }
        return jwtResponse;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        return Optional.of(userDto)
                .filter(dto -> userRepository.existsByLogin(dto.getLogin()))
                .map(userMapper::toEntity)
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User '" + userDto.getLogin() + "' already exist"));
    }

    @Override
    public JwtResponse refresh(HttpServletRequest request) {
        Cookie refreshToken = WebUtils.getCookie(request, "refreshToken");
        return Optional.ofNullable(refreshToken)
                .map(Cookie::getValue)
                .map(jwtUtil::getAccessTokenFromRefreshToken)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token not found"));
    }

    private Cookie getRefreshTokenCookie(LoginRequest loginRequest) {
        String refreshToken = jwtUtil.getRefreshToken(loginRequest.getLogin());
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        // TODO set true in production when using HTTPS
        cookie.setSecure(false);
        return cookie;
    }
}
