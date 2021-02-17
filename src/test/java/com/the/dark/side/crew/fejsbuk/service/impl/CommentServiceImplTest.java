package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.mapper.CommentMapper;
import com.the.dark.side.crew.fejsbuk.model.CommentEntity;
import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.CommentDto;
import com.the.dark.side.crew.fejsbuk.repository.CommentRepository;
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

class CommentServiceImplTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenPostFoundThenReturnAllComments() {
        long postId = 2L;
        CommentDto commentDto = mock(CommentDto.class);
        CommentEntity commentEntity = mock(CommentEntity.class);
        PostEntity postEntity = mock(PostEntity.class);

        when(commentRepository.findByPostEntityId(postId))
                .thenReturn(List.of(commentEntity));
        when(commentMapper.toDtos(List.of(commentEntity)))
                .thenReturn(List.of(commentDto));
        when(postEntity.getId()).thenReturn(postId);

        List<CommentDto> result = commentService.getComments(postId);
        assertEquals(1, result.size());
        assertEquals(commentDto, result.get(0));
    }

    @Test
    void whenPostNotFoundThenReturnEmptyList() {
        long postId = 2L;

        when(commentRepository.findByPostEntityId(postId))
                .thenReturn(Collections.emptyList());
        when(commentMapper.toDtos(Collections.emptyList()))
                .thenReturn(Collections.emptyList());

        List<CommentDto> result = commentService.getComments(postId);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldAddComment() {
        CommentDto commentDto = mock(CommentDto.class);
        CommentEntity commentEntity = mock(CommentEntity.class);

        when(commentMapper.toEntity(commentDto)).thenReturn(commentEntity);
        when(commentMapper.toDto(commentEntity)).thenReturn(commentDto);

        CommentDto result = commentService.addComment(commentDto);
        verify(commentRepository).save(commentEntity);
        assertEquals(result, commentDto);
    }
}