package com.the.dark.side.crew.fejsbuk.repository;

import com.the.dark.side.crew.fejsbuk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
