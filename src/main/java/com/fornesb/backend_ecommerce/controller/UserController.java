package com.fornesb.backend_ecommerce.controller;

import com.fornesb.backend_ecommerce.dto.LoginRequest;
import com.fornesb.backend_ecommerce.dto.LoginResponse;
import com.fornesb.backend_ecommerce.entity.User;
import com.fornesb.backend_ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }
    @GetMapping ("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id,
                                           @RequestBody User newUserData,
                                           @RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return ResponseEntity.ok(userService.updateUser(id, newUserData, token));
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id) ? "User deleted" : "User not found";
    }
}
