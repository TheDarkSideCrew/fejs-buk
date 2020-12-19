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

    // get users list
    @GetMapping("users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // get a specific user details
    @GetMapping("users/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    // register new user
    @PostMapping("users/new")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
