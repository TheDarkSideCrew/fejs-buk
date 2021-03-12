package com.the.dark.side.crew.fejsbuk.controller;

import com.the.dark.side.crew.fejsbuk.model.dto.LikeDto;
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
    public ResponseEntity<LikeDto> addLike(@RequestBody LikeDto likeDto) {
        return ResponseEntity.ok(likeService.addLike(likeDto));
    }

    @DeleteMapping
    public void removeLike(@RequestParam long likeId) {
        likeService.removeLike(likeId);
    }

    @GetMapping
    public ResponseEntity<Long> countLikes(@RequestParam long postId) {
        return ResponseEntity.ok(likeService.countLikes(postId));
    }
}
