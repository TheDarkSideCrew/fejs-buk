package com.the.dark.side.crew.fejsbuk.mapper;

import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.auth.domain.entity.UserEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;
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

class PostMapperTest {

    @InjectMocks
    private PostMapper postMapper;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenCallToDtosThenReturnListOfDtos() {
        PostMapper mapper = spy(new PostMapper(userRepository));
        PostDto postDto = mock(PostDto.class);
        PostEntity postEntity = mock(PostEntity.class);

        doReturn(postDto).when(mapper).toDto(postEntity);

        List<PostDto> result = mapper.toDtos(List.of(postEntity));
        assertEquals(1, result.size());
        assertEquals(postDto, result.get(0));
    }

    @Test
    void whenCallToDtoThenReturnPostDto() {
        PostEntity postEntity = mock(PostEntity.class);
        UserEntity user = mock(UserEntity.class);
        LocalDateTime createdAt = LocalDateTime.now();
        long postId = 1L;
        long userId = 2L;
        String message = "message";

        when(postEntity.getId()).thenReturn(postId);
        when(postEntity.getMessage()).thenReturn(message);
        when(postEntity.getCreatedAt()).thenReturn(createdAt);
        when(postEntity.getUserEntity()).thenReturn(user);
        when(user.getId()).thenReturn(userId);

        PostDto result = postMapper.toDto(postEntity);
        assertEquals(postId, result.getId());
        assertEquals(message, result.getMessage());
        assertEquals(createdAt, result.getCreatedAt());
        assertEquals(userId, result.getUserId());
    }

    @Test
    void whenUserFoundThenReturnPost() {
        long userId = 2L;
        String message = "message";
        UserEntity userEntity = mock(UserEntity.class);
        PostDto postDto = mock(PostDto.class);

        when(postDto.getMessage()).thenReturn(message);
        when(postDto.getUserId()).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userEntity.getId()).thenReturn(userId);

        PostEntity result = postMapper.toEntity(postDto);
        assertEquals(message, result.getMessage());
        assertEquals(userId, result.getUserEntity().getId());
    }

    @Test
    void whenUserNotFoundThenReturn404() {
        long userId = 2L;
        PostDto postDto = mock(PostDto.class);

        when(postDto.getUserId()).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> postMapper.toEntity(postDto));
        assertEquals("404 NOT_FOUND \"User 2 not found.\"", exception.getMessage());
    }
}