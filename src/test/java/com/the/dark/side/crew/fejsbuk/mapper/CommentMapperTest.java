package com.the.dark.side.crew.fejsbuk.mapper;

import com.the.dark.side.crew.fejsbuk.model.CommentEntity;
import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.auth.domain.entity.UserEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.CommentDto;
import com.the.dark.side.crew.fejsbuk.repository.PostRepository;
import com.the.dark.side.crew.fejsbuk.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CommentMapperTest {

    @InjectMocks
    private CommentMapper commentMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenToDtosThenReturnListOfDtos() {
        CommentMapper mapper = spy(new CommentMapper(userRepository, postRepository));
        CommentDto commentDto = mock(CommentDto.class);
        CommentEntity commentEntity = mock(CommentEntity.class);

        doReturn(commentDto).when(mapper).toDto(commentEntity);

        List<CommentDto> result = mapper.toDtos(List.of(commentEntity));
        assertEquals(1, result.size());
        assertEquals(commentDto, result.get(0));
    }

    @Test
    void whenToDtoThenReturnPostDto() {
        CommentEntity commentEntity = mock(CommentEntity.class);
        PostEntity postEntity = mock(PostEntity.class);
        UserEntity userEntity = mock(UserEntity.class);
        LocalDateTime createdAt = LocalDateTime.now();
        long commentId = 3L;
        long postId = 1L;
        long userId = 2L;
        String message = "message";

        when(commentEntity.getId()).thenReturn(commentId);
        when(commentEntity.getMessage()).thenReturn(message);
        when(commentEntity.getCreatedAt()).thenReturn(createdAt);
        when(commentEntity.getUserEntity()).thenReturn(userEntity);
        when(commentEntity.getPostEntity()).thenReturn(postEntity);
        when(userEntity.getId()).thenReturn(userId);
        when(postEntity.getId()).thenReturn(postId);

        CommentDto result = commentMapper.toDto(commentEntity);
        assertEquals(commentId, result.getId());
        assertEquals(message, result.getMessage());
        assertEquals(createdAt, result.getCreatedAt());
        assertEquals(postId, result.getPostId());
        assertEquals(userId, result.getUserId());
    }

    @Test
    void whenUserAndPostFoundThenReturnComment() {
        long userId = 1L;
        long postId = 2L;
        String message = "message";
        UserEntity userEntity = mock(UserEntity.class);
        PostEntity postEntity = mock(PostEntity.class);
        CommentDto commentDto = mock(CommentDto.class);

        when(commentDto.getMessage()).thenReturn(message);
        when(commentDto.getUserId()).thenReturn(userId);
        when(commentDto.getPostId()).thenReturn(postId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(postRepository.findById(postId)).thenReturn(Optional.of(postEntity));
        when(userEntity.getId()).thenReturn(userId);
        when(postEntity.getId()).thenReturn(postId);

        CommentEntity result = commentMapper.toEntity(commentDto);
        assertEquals(message, result.getMessage());
        assertEquals(userEntity, result.getUserEntity());
        assertEquals(postEntity, result.getPostEntity());

    }

    @Test
    void whenUserNotFoundThenReturn404() {
        long userId = 2L;
        CommentDto commentDto = mock(CommentDto.class);

        when(commentDto.getUserId()).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> commentMapper.toEntity(commentDto));
        assertEquals("404 NOT_FOUND \"User 2 not found.\"", exception.getMessage());
    }

    @Test
    void whenPostNotFoundThenReturn404() {
        long postId = 2L;
        long userId = 2L;
        UserEntity userEntity = mock(UserEntity.class);
        CommentDto commentDto = mock(CommentDto.class);

        when(commentDto.getPostId()).thenReturn(postId);
        when(commentDto.getUserId()).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(postRepository.findById(postId)).thenReturn(Optional.empty());
        when(userEntity.getId()).thenReturn(userId);

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> commentMapper.toEntity(commentDto));
        assertEquals("404 NOT_FOUND \"Post 2 not found.\"", exception.getMessage());
    }
}