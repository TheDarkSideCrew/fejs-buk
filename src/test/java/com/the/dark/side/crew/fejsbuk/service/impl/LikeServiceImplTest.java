package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.mapper.LikeMapper;
import com.the.dark.side.crew.fejsbuk.model.LikeEntity;
import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.model.UserEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.LikeDto;
import com.the.dark.side.crew.fejsbuk.repository.LikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LikeServiceImplTest {

    long userId = 1L;
    long postId = 2L;
    long likeId = 3L;

    @InjectMocks
    private LikeServiceImpl likeService;

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private LikeMapper likeMapper;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldAddLike() {
        LikeDto likeDto = mock(LikeDto.class);
        LikeEntity likeEntity = mock(LikeEntity.class);
        PostEntity postEntity = mock(PostEntity.class);
        UserEntity userEntity = mock(UserEntity.class);

        when(likeRepository.existsByUserEntityIdAndPostEntityId(userId, postId)).thenReturn(false);
        when(likeEntity.getUserEntity()).thenReturn(userEntity);
        when(likeEntity.getPostEntity()).thenReturn(postEntity);
        when(likeEntity.getUserEntity().getId()).thenReturn(userId);
        when(likeEntity.getPostEntity().getId()).thenReturn(postId);
        when(likeMapper.toEntity(likeDto)).thenReturn(likeEntity);
        when(likeMapper.toDto(likeEntity)).thenReturn(likeDto);

        LikeDto result = likeService.addLike(likeDto);
        verify(likeRepository).save(likeEntity);
        assertEquals(result, likeDto);
    }

    @Test
    void whenAlreadyLikedThen400() {
        PostEntity postEntity = mock(PostEntity.class);
        UserEntity userEntity = mock(UserEntity.class);
        LikeEntity likeEntity = mock(LikeEntity.class);
        LikeDto likeDto = mock(LikeDto.class);

        when(likeEntity.getUserEntity()).thenReturn(userEntity);
        when(likeEntity.getPostEntity()).thenReturn(postEntity);
        when(likeEntity.getUserEntity().getId()).thenReturn(userId);
        when(likeEntity.getPostEntity().getId()).thenReturn(postId);
        when(likeMapper.toEntity(likeDto)).thenReturn(likeEntity);
        when(likeRepository.existsByUserEntityIdAndPostEntityId(userId, postId)).thenReturn(true);

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> likeService.addLike(likeDto));
        assertEquals("409 CONFLICT \"Already liked.\"", exception.getMessage());
    }

    @Test
    void shouldRemoveLike() {
        LikeEntity likeEntity = mock(LikeEntity.class);

        when(likeEntity.getId()).thenReturn(likeId);

        likeRepository.deleteById(likeEntity.getId());

        verify(likeRepository).deleteById(likeId);
    }

    @Test
    void whenLikeNotExistThen404() {
        when(likeRepository.existsById(likeId)).thenReturn(false);

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> likeService.removeLike(likeId));
        assertEquals("404 NOT_FOUND \"Like 3 does not exist.\"", exception.getMessage());
    }

    @Test
    void countLikes() {
        long count = 1L;
        PostEntity postEntity = mock(PostEntity.class);

        when(postEntity.getId()).thenReturn(postId);
        when(likeRepository.countByPostEntityId(postId)).thenReturn(count);

        Long result = likeService.countLikes(postId);
        assertEquals(result, count);
    }
}