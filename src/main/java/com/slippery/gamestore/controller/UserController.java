package com.slippery.gamestore.controller;

import com.slippery.gamestore.dto.UsersDto;
import com.slippery.gamestore.models.Users;
import com.slippery.gamestore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @PostMapping("/register")
    public ResponseEntity<UsersDto> registerUser(@RequestBody Users userDetails) {
        return ResponseEntity.ok(service.registerUser(userDetails));
    }
    @PutMapping("/login")
    public ResponseEntity<UsersDto> login(@RequestBody Users userDetails) {
        return ResponseEntity.ok(service.login(userDetails));
    }
    @PutMapping("/update")
    public ResponseEntity<UsersDto> updateUser(@RequestBody Users userDetails,@RequestParam Long userId) {
        return ResponseEntity.ok(service.updateUser(userDetails, userId));
    }
    @GetMapping("/get/{userId}")
    public ResponseEntity<UsersDto> findUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findUserById(userId));
    }
    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<UsersDto> deleteUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(service.deleteUserById(userId));
    }
}
