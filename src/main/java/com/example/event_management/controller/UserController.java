package com.example.event_management.controller;

import com.example.event_management.model.EventRegistration;
import com.example.event_management.model.User;
import com.example.event_management.service.EventRegistrationService;
import com.example.event_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EventRegistrationService registrationService;

    // POST /api/users/register - Регистрация пользователя
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    // POST /api/users/login - Логин пользователя (по email)
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        return ResponseEntity.ok(userService.loginUser(email));
    }
}
