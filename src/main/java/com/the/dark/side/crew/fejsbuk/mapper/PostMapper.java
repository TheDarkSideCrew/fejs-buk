package com.the.dark.side.crew.fejsbuk.mapper;

import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import com.the.dark.side.crew.fejsbuk.auth.domain.entity.UserEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.PostDto;
import com.the.dark.side.crew.fejsbuk.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostMapper {

    private final UserRepository userRepository;

    public List<PostDto> toDtos(List<PostEntity> allPosts) {
        return allPosts.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PostDto toDto(PostEntity postEntity) {
        return PostDto.builder()
                .id(postEntity.getId())
                .message(postEntity.getMessage())
                .createdAt(postEntity.getCreatedAt())
                .userId(postEntity.getUserEntity().getId())
                .build();
    }

    public PostEntity toEntity(PostDto postDto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setMessage(postDto.getMessage());
        long userId = postDto.getUserId();
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + userId + " not found."));
        postEntity.setUserEntity(userEntity);
        return postEntity;
    }
}
