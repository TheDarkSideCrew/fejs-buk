package com.the.dark.side.crew.fejsbuk.auth.service.imp;

import com.the.dark.side.crew.fejsbuk.auth.domain.dto.JwtResponse;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.LoginRequest;
import com.the.dark.side.crew.fejsbuk.auth.domain.dto.UserDto;
import com.the.dark.side.crew.fejsbuk.auth.mapper.UserMapper;
import com.the.dark.side.crew.fejsbuk.auth.service.AuthService;
import com.the.dark.side.crew.fejsbuk.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        return Optional.of(userDto)
                .filter(dto -> userRepository.existByLogin(dto.getLogin()))
                .map(userMapper::toEntity)
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User '" + userDto.getLogin() + "' already exist"));
    }
}
