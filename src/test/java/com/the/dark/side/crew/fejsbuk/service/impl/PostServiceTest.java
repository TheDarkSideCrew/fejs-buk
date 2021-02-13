package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.mapper.PostMapper;
import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.model.UserEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;
import com.the.dark.side.crew.fejsbuk.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostMapper postMapper;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenUserFoundThenReturnAllPosts() {
        long userId = 2L;
        PostDto postDto = mock(PostDto.class);
        PostEntity postEntity = mock(PostEntity.class);
        UserEntity userEntity = mock(UserEntity.class);

        when(postRepository.findByUserEntityId(userId))
                .thenReturn(List.of(postEntity));
        when(postMapper.toDtos(List.of(postEntity)))
                .thenReturn(Collections.singletonList(postDto));
        when(userEntity.getId()).thenReturn(userId);

        List<PostDto> result = postService.getAllUserIdPosts(userId);
        assertEquals(1, result.size());
        assertEquals(postDto, result.get(0));
    }

    @Test
    void whenUserNotFoundThenReturnEmptyList() {
        long userId = 2L;

        when(postRepository.findByUserEntityId(userId))
                .thenReturn(Collections.emptyList());
        when(postMapper.toDtos(Collections.emptyList()))
                .thenReturn(Collections.emptyList());

        List<PostDto> result = postService.getAllUserIdPosts(userId);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldGetAllPosts() {
        PostDto postDto = mock(PostDto.class);
        PostEntity postEntity = mock(PostEntity.class);

        when(postRepository.findAll()).thenReturn(List.of(postEntity));
        when(postMapper.toDtos(List.of(postEntity)))
                .thenReturn(List.of(postDto));

        List<PostDto> result = postService.getAllPosts();
        assertEquals(1, result.size());
        assertEquals(postDto, result.get(0));
    }

    @Test
    void shouldAddPost() {
        PostDto postDto = mock(PostDto.class);
        PostEntity postEntity = mock(PostEntity.class);

        when(postMapper.toEntity(postDto)).thenReturn(postEntity);
        when(postMapper.toDto(postEntity)).thenReturn(postDto);

        PostDto result = postService.addPost(postDto);
        verify(postRepository).save(postEntity);
        assertEquals(result, postDto);
    }
}
