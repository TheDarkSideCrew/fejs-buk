package com.the.dark.side.crew.fejsbuk.service;


import com.the.dark.side.crew.fejsbuk.model.dto.LikeDto;

public interface LikeService {

    LikeDto addLike(LikeDto likeDto);

    void removeLike(long likeId);

    Long countLikes(long postId);
}
