package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.auth.domain.entity.UserEntity;
import com.the.dark.side.crew.fejsbuk.auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenUserFoundThenReturnUser() {
        long userId = 1L;
        UserEntity userEntity = mock(UserEntity.class);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        Optional<UserEntity> result = userService.getUser(userId);
        assertTrue(result.isPresent());
        assertEquals(userEntity, result.get());
    }

    @Test
    void whenUserNotFoundThenEmpty() {
        long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<UserEntity> result = userService.getUser(userId);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldAddUser() {
        UserEntity user = mock(UserEntity.class);

        when(userRepository.save(user)).thenReturn(user);

        UserEntity userEntity = userService.addUser(user);
        verify(userRepository).save(user);
        assertEquals(userEntity, user);
    }

}