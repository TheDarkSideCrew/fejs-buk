package com.the.dark.side.crew.fejsbuk.controller;

import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;
import com.the.dark.side.crew.fejsbuk.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<PostDto> addLike(@RequestParam long postId, @RequestParam long userId) {
        return ResponseEntity.ok(likeService.addLike(postId, userId));
    }

    @GetMapping
    public ResponseEntity<Integer> countLikes(@RequestParam long postId) {
        return ResponseEntity.ok(likeService.countLikes(postId));
    }
}
