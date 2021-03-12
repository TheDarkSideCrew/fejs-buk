package com.the.dark.side.crew.fejsbuk.mapper;

import com.the.dark.side.crew.fejsbuk.model.LikeEntity;
import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.model.UserEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.LikeDto;
import com.the.dark.side.crew.fejsbuk.repository.PostRepository;
import com.the.dark.side.crew.fejsbuk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LikeMapper {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeDto toDto(LikeEntity likeEntity) {
        return LikeDto.builder()
                .id(likeEntity.getId())
                .createdAt(likeEntity.getCreatedAt())
                .userId(likeEntity.getUserEntity().getId())
                .postId(likeEntity.getPostEntity().getId())
                .build();
    }

    public LikeEntity toEntity(LikeDto likeDto) {
        LikeEntity likeEntity = new LikeEntity();
        long userId = likeDto.getUserId();
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "User " + userId + " not found."));
        likeEntity.setUserEntity(userEntity);
        long postId = likeDto.getPostId();
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND , "Post " + postId + " not found."));
        likeEntity.setPostEntity(postEntity);
        return likeEntity;
    }
}