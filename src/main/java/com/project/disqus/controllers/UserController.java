package com.project.disqus.controllers;

import com.project.disqus.services.UserService;
import org.springframework.web.bind.annotation.*;

import com.project.disqus.entities.User;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();

    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.saveOneUser(newUser);

    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        //custom exception
        return userService.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable long userId, @RequestBody User newUser) {
        return userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {

        userService.deleteById(userId);
    }


}
