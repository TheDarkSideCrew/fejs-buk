package com.the.dark.side.crew.fejsbuk.service;

import java.util.Map;

public interface LikeService {

    String addLike(long postId, long userId);

    Map<String, Integer> countLikes(long postId);
}
