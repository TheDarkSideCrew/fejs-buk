package com.the.dark.side.crew.fejsbuk.service;

import com.the.dark.side.crew.fejsbuk.model.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getAllPostIdComments(long postId);

    CommentDto addComment(CommentDto commentDto);
}
