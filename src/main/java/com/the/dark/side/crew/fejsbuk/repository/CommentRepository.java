package com.the.dark.side.crew.fejsbuk.repository;

import com.the.dark.side.crew.fejsbuk.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByPostEntityId(Long id);
}
