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
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class PostServiceTest {

    @InjectMocks
    private PostServiceImpl postService;

    @InjectMocks
    private PostMapper postMapper;

    @Spy
    private PostRepository postRepository;

    @Spy
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void shouldReturnAllPosts() {
//        // given
//        long userId = 1L;
//        PostDto postDto = mock(PostDto.class);
//        postDto.setUserId(userId);
//
////        when(postRepository.findByUserEntityId(userId)).thenReturn(List.of(postEntity));
//        when(postMapper.toDtos(postRepository.findByUserEntityId(userId))).thenReturn(List.of(postDto));
//
//        List<PostDto> result = postService.getAllPosts(userId);
//        assertTrue(result.);
//        assertArrayEquals(result, postDto);
//    }

    @Test
    void shouldAddPost() {
        // given
        PostDto postDto = spy(PostDto.class);
        PostEntity postEntity = spy(PostEntity.class);
        UserEntity userEntity = spy(UserEntity.class);
        userEntity.setId(1);
        postDto.setUserId(1);
        postEntity.setId(1);
        postEntity.setUserEntity(userEntity);

        // when
        when(postRepository.save(postMapper.toEntity(postDto)))
                .thenReturn(postEntity);

        // then
//        PostDto post = postMapper.toDto(postService.addPost(postDto));
//        post.setUserId(1);
//        verify(postRepository).save(postMapper.toEntity(postDto));
//        assertEquals(post, postDto);
    }
}
