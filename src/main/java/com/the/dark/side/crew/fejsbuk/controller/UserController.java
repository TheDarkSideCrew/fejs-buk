package com.the.dark.side.crew.fejsbuk.controller;

import com.the.dark.side.crew.fejsbuk.model.User;
import com.the.dark.side.crew.fejsbuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("register")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
