package com.the.dark.side.crew.fejsbuk.mapper;

import com.the.dark.side.crew.fejsbuk.model.LikeEntity;
import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.model.UserEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.LikeDto;
import com.the.dark.side.crew.fejsbuk.repository.PostRepository;
import com.the.dark.side.crew.fejsbuk.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LikeMapperTest {

    long postId = 1L;
    long userId = 2L;
    long likeId = 3L;

    @InjectMocks
    private LikeMapper likeMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenToDtoThenReturnLikeDto() {
        LikeEntity likeEntity = mock(LikeEntity.class);
        PostEntity postEntity = mock(PostEntity.class);
        UserEntity userEntity = mock(UserEntity.class);
        LocalDateTime createdAt = LocalDateTime.now();

        when(likeEntity.getId()).thenReturn(likeId);
        when(likeEntity.getCreatedAt()).thenReturn(createdAt);
        when(likeEntity.getUserEntity()).thenReturn(userEntity);
        when(likeEntity.getPostEntity()).thenReturn(postEntity);
        when(userEntity.getId()).thenReturn(userId);
        when(postEntity.getId()).thenReturn(postId);

        LikeDto result = likeMapper.toDto(likeEntity);
        assertEquals(likeId, result.getId());
        assertEquals(createdAt, result.getCreatedAt());
        assertEquals(postId, result.getPostId());
        assertEquals(userId, result.getUserId());
    }

    @Test
    void whenUserAndPostFoundThenReturnLike() {
        UserEntity userEntity = mock(UserEntity.class);
        PostEntity postEntity = mock(PostEntity.class);
        LikeDto likeDto = mock(LikeDto.class);

        when(likeDto.getUserId()).thenReturn(userId);
        when(likeDto.getPostId()).thenReturn(postId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(postRepository.findById(postId)).thenReturn(Optional.of(postEntity));
        when(userEntity.getId()).thenReturn(userId);
        when(postEntity.getId()).thenReturn(postId);

        LikeEntity result = likeMapper.toEntity(likeDto);
        assertEquals(userEntity, result.getUserEntity());
        assertEquals(postEntity, result.getPostEntity());
    }

    @Test
    void whenUserNotFoundThenReturn404() {
        LikeDto likeDto = mock(LikeDto.class);

        when(likeDto.getUserId()).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> likeMapper.toEntity(likeDto));
        assertEquals("404 NOT_FOUND \"User 2 not found.\"", exception.getMessage());
    }

    @Test
    void whenPostNotFoundThenReturn404() {
        UserEntity userEntity = mock(UserEntity.class);
        LikeDto likeDto = mock(LikeDto.class);

        when(likeDto.getPostId()).thenReturn(postId);
        when(likeDto.getUserId()).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(postRepository.findById(postId)).thenReturn(Optional.empty());
        when(userEntity.getId()).thenReturn(userId);

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> likeMapper.toEntity(likeDto));
        assertEquals("404 NOT_FOUND \"Post 1 not found.\"", exception.getMessage());
    }
}