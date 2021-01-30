package com.the.dark.side.crew.fejsbuk.service;

import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts(long id);

    PostEntity addPost(PostDto postEntity);
}
