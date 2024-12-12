package org.example.springbootdemo.controller;

import org.example.springbootdemo.models.request.UserRequest;
import org.example.springbootdemo.models.response.UserResponse;
import org.example.springbootdemo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest req) {
        return userService.createUser(req);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id ,@RequestBody UserRequest req) {
        return userService.updateUser(id, req);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
