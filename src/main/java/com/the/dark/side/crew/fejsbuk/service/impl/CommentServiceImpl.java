package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.mapper.CommentMapper;
import com.the.dark.side.crew.fejsbuk.model.CommentEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.CommentDto;
import com.the.dark.side.crew.fejsbuk.repository.CommentRepository;
import com.the.dark.side.crew.fejsbuk.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> getComments(long postId) {
        return commentMapper.toDtos(commentRepository.findByPostEntityId(postId));
    }

    @Override
    public CommentDto addComment(CommentDto commentDto) {
        CommentEntity commentEntity = commentMapper.toEntity(commentDto);
        commentRepository.save(commentEntity);
        return commentMapper.toDto(commentEntity);
    }
}
