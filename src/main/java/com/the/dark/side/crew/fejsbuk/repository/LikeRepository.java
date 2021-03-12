package com.the.dark.side.crew.fejsbuk.repository;

import com.the.dark.side.crew.fejsbuk.model.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    Long countByPostEntityId(long id);

    Boolean existsByUserEntityIdAndPostEntityId(long userId, long postId);
}