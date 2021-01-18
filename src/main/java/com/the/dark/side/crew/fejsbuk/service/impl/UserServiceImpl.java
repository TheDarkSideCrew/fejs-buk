package com.the.dark.side.crew.fejsbuk.service.impl;

import com.the.dark.side.crew.fejsbuk.model.UserEntity;
import com.the.dark.side.crew.fejsbuk.repository.UserRepository;
import com.the.dark.side.crew.fejsbuk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> getUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
