package com.the.dark.side.crew.fejsbuk.controller;


import com.the.dark.side.crew.fejsbuk.mapper.PostMapper;
import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;
import com.the.dark.side.crew.fejsbuk.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostServiceImpl postService;
    private final PostMapper postMapper;

    @GetMapping
    public List<PostDto> getAllPosts(@RequestParam long userId) {
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(postMapper.toDto(postService.addPost(postDto)));
    }
}
