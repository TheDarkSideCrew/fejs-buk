package com.the.dark.side.crew.fejsbuk.auth.repository;

import com.the.dark.side.crew.fejsbuk.auth.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
