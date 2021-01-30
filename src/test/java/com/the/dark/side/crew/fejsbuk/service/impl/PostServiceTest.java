package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.mapper.PostMapper;
import com.the.dark.side.crew.fejsbuk.repository.PostRepository;
import com.the.dark.side.crew.fejsbuk.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PostServiceTest {

    @InjectMocks
    private PostServiceImpl postService;

    @InjectMocks
    private PostMapper postMapper;

    @Mock
    private PostRepository postRepository;

    @Mock
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

//    @Test
//    void shouldAddPost() {
//        // given
//        PostDto postDto = mock(PostDto.class);
//        PostEntity postEntity = mock(PostEntity.class);
//
//        // when
//        when(postRepository.save(postMapper.toEntity(postDto)))
//                .thenReturn(postEntity);
//
//        // then
//        PostDto post = postService.addPost(postDto.);
//        verify(postRepository).save(postMapper.toEntity(postDto));
//        assertEquals(postDto, post);
//    }
}
