package com.the.dark.side.crew.fejsbuk.service;

import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllUserIdPosts(long userId);

    List<PostDto> getAllPosts();

    PostDto addPost(PostDto postDto);
}
