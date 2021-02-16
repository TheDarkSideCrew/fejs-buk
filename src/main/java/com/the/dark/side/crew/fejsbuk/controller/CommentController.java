package com.the.dark.side.crew.fejsbuk.controller;

import com.the.dark.side.crew.fejsbuk.model.dto.CommentDto;
import com.the.dark.side.crew.fejsbuk.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> getComments(@RequestParam long postId) {
        return commentService.getComments(postId);
    }

    @PostMapping
    public ResponseEntity<CommentDto> addComment (@RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.addComment(commentDto));
    }
}
