package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.mapper.PostMapper;
import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;
import com.the.dark.side.crew.fejsbuk.repository.PostRepository;
import com.the.dark.side.crew.fejsbuk.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public List<PostDto> getAllUserIdPosts(long userId) {
        return  postMapper.toDtos(postRepository.findByUserEntityId(userId));
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postMapper.toDtos(postRepository.findAll());
    }

    @Override
    public PostDto addPost(PostDto postDto) {
        PostEntity postEntity = postMapper.toEntity(postDto);
        postRepository.save(postEntity);
        return postMapper.toDto(postEntity);
    }
}
