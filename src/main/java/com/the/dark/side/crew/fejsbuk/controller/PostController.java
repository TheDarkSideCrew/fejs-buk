package com.the.dark.side.crew.fejsbuk.controller;


import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;
import com.the.dark.side.crew.fejsbuk.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping(params = {"userId"})
    public List<PostDto> getAllUserIdPosts(@RequestParam long userId) {
        return postService.getAllUserIdPosts(userId);
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.addPost(postDto));
    }
}
