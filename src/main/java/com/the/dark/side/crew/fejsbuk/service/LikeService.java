package com.the.dark.side.crew.fejsbuk.service;


import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;

public interface LikeService {

    PostDto addLike(long postId, long userId);

    Integer countLikes(long postId);
}
