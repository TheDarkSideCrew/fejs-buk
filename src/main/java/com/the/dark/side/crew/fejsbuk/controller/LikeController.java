package com.the.dark.side.crew.fejsbuk.controller;

import com.the.dark.side.crew.fejsbuk.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<String> addLike
            (@RequestParam long postId, @RequestParam  long userId) {
        return ResponseEntity.ok(likeService.addLike(postId, userId));
    }

    @GetMapping
    public ResponseEntity<Map<String, Integer>> countLikes(@RequestParam long postId) {
        return ResponseEntity.ok(likeService.countLikes(postId));
    }
}