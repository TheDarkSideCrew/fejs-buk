package com.the.dark.side.crew.fejsbuk.repository;

import com.the.dark.side.crew.fejsbuk.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

   List<PostEntity> findByUserEntityId(Long id);
}
