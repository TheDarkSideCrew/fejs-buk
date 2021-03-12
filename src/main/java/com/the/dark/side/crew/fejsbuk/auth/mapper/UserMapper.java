package com.the.dark.side.crew.fejsbuk.auth.mapper;

import com.the.dark.side.crew.fejsbuk.auth.domain.dto.UserDto;
import com.the.dark.side.crew.fejsbuk.auth.domain.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserEntity toEntity(UserDto dto) {
        return UserEntity.builder()
                .login(dto.getLogin())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }

    public UserDto toDto(UserEntity entity) {
        return UserDto.builder()
                .login(entity.getLogin())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
}
