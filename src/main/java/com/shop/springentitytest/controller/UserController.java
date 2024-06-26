package com.shop.springentitytest.controller;

import com.shop.springentitytest.entity.UserEntity;
import com.shop.springentitytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to retrieve all users
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    // Endpoint to retrieve a user by ID
    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    // Endpoint to create a new user
    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    // Endpoint to update an existing user
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable String id, @RequestBody UserEntity user) {
        user.setId(id); // Ensure the user ID is set from the path variable
        return userService.saveUser(user);
    }

    // Endpoint to delete a user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
