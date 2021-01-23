package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.model.UserEntity;
import com.the.dark.side.crew.fejsbuk.repository.UserRepository;
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
    void shouldReturnUser() {
        // given
        long userId = 1L;
        UserEntity userEntity = mock(UserEntity.class);

        //when
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        //then
        Optional<UserEntity> result = userService.getUser(userId);
        assertTrue(result.isPresent());
        assertEquals(userEntity, result.get());
    }

    @Test
    void shouldAddUser() {
        //given
        UserEntity user = mock(UserEntity.class);

        //when
        when(userRepository.save(user)).thenReturn(user);

        //then
        UserEntity userEntity = userService.addUser(user);
        verify(userRepository).save(user);
        assertEquals(userEntity, user);
    }

}