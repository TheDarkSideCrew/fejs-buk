package com.the.dark.side.crew.fejsbuk.mapper;

import com.the.dark.side.crew.fejsbuk.model.CommentEntity;
import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.auth.domain.entity.UserEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.CommentDto;
import com.the.dark.side.crew.fejsbuk.repository.PostRepository;
import com.the.dark.side.crew.fejsbuk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentMapper {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<CommentDto> toDtos(List<CommentEntity> allComments) {
        return allComments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CommentDto toDto(CommentEntity commentEntity) {
        return CommentDto.builder()
                .id(commentEntity.getId())
                .message(commentEntity.getMessage())
                .createdAt(commentEntity.getCreatedAt())
                .userId(commentEntity.getUserEntity().getId())
                .postId(commentEntity.getPostEntity().getId())
                .build();
    }

    public CommentEntity toEntity(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setMessage(commentDto.getMessage());
        long userId = commentDto.getUserId();
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "User " + userId + " not found."));
        commentEntity.setUserEntity(userEntity);
        long postId = commentDto.getPostId();
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND , "Post " + postId + " not found."));
        commentEntity.setPostEntity(postEntity);
        return commentEntity;
    }
}
