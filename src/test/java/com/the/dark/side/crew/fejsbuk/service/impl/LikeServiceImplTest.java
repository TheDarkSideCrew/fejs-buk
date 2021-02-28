package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.mapper.PostMapper;
import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.model.UserEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;
import com.the.dark.side.crew.fejsbuk.repository.PostRepository;
import com.the.dark.side.crew.fejsbuk.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LikeServiceImplTest {

    @InjectMocks
    private LikeServiceImpl likeService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostMapper postMapper;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenAddLikeReturnUser() {
        long postId = 1L;
        long userId = 1L;
        PostEntity postEntity = mock(PostEntity.class);
        UserEntity userEntity = mock(UserEntity.class);
        PostDto postDto = mock(PostDto.class);

        when(postRepository.findById(postId)).thenReturn(Optional.of(postEntity));
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(postMapper.toDto(postEntity)).thenReturn(postDto);

        PostDto result = likeService.addLike(postId, userId);
        verify(postRepository).save(postEntity);
        assertEquals(result, postDto);
    }

    @Test
    void whenPostNotFoundThenReturn404() {
        long postId = 1L;
        long userId = 1L;
        PostEntity postEntity = mock(PostEntity.class);

        when(postEntity.getId()).thenReturn(postId);
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> likeService.addLike(postId, userId));
        assertEquals("404 NOT_FOUND \"Post 1 not found.\"", exception.getMessage());
    }

    @Test
    void whenUserNotFoundThenReturn404() {
        long postId = 1L;
        long userId = 1L;
        PostEntity postEntity = mock(PostEntity.class);
        UserEntity userEntity = mock(UserEntity.class);

        when(postEntity.getId()).thenReturn(postId);
        when(userEntity.getId()).thenReturn(userId);
        when(postRepository.findById(postId)).thenReturn(Optional.of(postEntity));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> likeService.addLike(postId, userId));
        assertEquals("404 NOT_FOUND \"User 1 not found.\"", exception.getMessage());
    }

    @Test
    void countLikes() {
        long postId = 1L;
        int count = 1;
        PostEntity postEntity = mock(PostEntity.class);
        UserEntity userEntity = mock(UserEntity.class);

        when(postEntity.getId()).thenReturn(postId);
        when(postEntity.getLikes()).thenReturn(Set.of(userEntity));
        when(postRepository.findById(postId)).thenReturn(Optional.of(postEntity));

        Integer result = likeService.countLikes(postId);
        assertEquals(result, count);
    }
}