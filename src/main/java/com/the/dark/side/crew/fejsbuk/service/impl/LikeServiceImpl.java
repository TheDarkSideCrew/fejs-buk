package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.mapper.LikeMapper;
import com.the.dark.side.crew.fejsbuk.model.LikeEntity;
import com.the.dark.side.crew.fejsbuk.model.dto.LikeDto;
import com.the.dark.side.crew.fejsbuk.repository.LikeRepository;
import com.the.dark.side.crew.fejsbuk.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    @Override
    public LikeDto addLike(LikeDto likeDto) {
        LikeEntity likeEntity = likeMapper.toEntity(likeDto);
        long userId = likeEntity.getUserEntity().getId();
        long postId = likeEntity.getPostEntity().getId();
        if(likeRepository.existsByUserEntityIdAndPostEntityId(userId, postId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already liked.");
        } else {
            likeRepository.save(likeEntity);
        }
        return likeMapper.toDto(likeEntity);
    }

    @Override
    public void removeLike(long likeId) {
        if(likeRepository.existsById(likeId)) {
            likeRepository.deleteById(likeId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like " + likeId + " does not exist.");
        }
    }

    @Override
    public Long countLikes(long postId) {
        return likeRepository.countByPostEntityId(postId);
    }
}