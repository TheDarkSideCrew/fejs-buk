package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.model.UserEntity;
import com.the.dark.side.crew.fejsbuk.repository.PostRepository;
import com.the.dark.side.crew.fejsbuk.repository.UserRepository;
import com.the.dark.side.crew.fejsbuk.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public String addLike(long postId, long userId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Post " + postId + " not found."));
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "User " + userId + " not found."));
        postEntity.getLikes().add(userEntity);
        postRepository.save(postEntity);
        return "User " + userId + " like post " + postId + ".";
    }

    @Override
    public Map<String, Integer> countLikes(long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Post " + postId + " not found."));
        return Collections.singletonMap("likes", postEntity.getLikes().size());
    }
}
