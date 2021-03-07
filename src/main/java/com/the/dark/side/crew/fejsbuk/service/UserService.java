package com.the.dark.side.crew.fejsbuk.service;

import com.the.dark.side.crew.fejsbuk.auth.domain.entity.UserEntity;
import java.util.Optional;

public interface UserService {

    Optional<UserEntity> getUser(long id);

    UserEntity addUser(UserEntity userEntity);
}
