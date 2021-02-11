package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.mapper.PostMapper;
import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;
import com.the.dark.side.crew.fejsbuk.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void whenUserFoundThanReturnAllPosts() {
        long userId = 1L;
        PostDto postDto = mock(PostDto.class);

        when(postMapper.toDtos(postRepository.findByUserEntityId(userId))).thenReturn(List.of(postDto));

        List<PostDto> result = postService.getAllPosts(userId);
        assertEquals(1, result.size());
        assertEquals(postDto, result.get(0));
    }

    @Test
    void whenUserNotFoundThanReturnEmptyList() {
        long userId = 1L;

        when(postMapper.toDtos(postRepository.findByUserEntityId(userId))).thenReturn(List.of());

        List<PostDto> result = postService.getAllPosts(userId);
        assertEquals(0, result.size());
    }

    @Test
    void shouldAddPost() {
        PostDto postDto = mock(PostDto.class);
        PostEntity postEntity = mock(PostEntity.class);

        when(postMapper.toEntity(postDto)).thenReturn(postEntity);
        when(postRepository.save(postEntity)).thenReturn(postEntity);
        when(postMapper.toDto(postEntity)).thenReturn(postDto);

        PostDto result = postService.addPost(postDto);
        assertEquals(result, postDto);
    }
}
