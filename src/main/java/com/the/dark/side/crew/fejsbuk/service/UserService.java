package com.the.dark.side.crew.fejsbuk.service;

import com.the.dark.side.crew.fejsbuk.model.User;
import com.the.dark.side.crew.fejsbuk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
