package com.example.event_management.service;

import com.example.event_management.model.User;
import com.example.event_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Dieser Service bietet Methoden zur Verwaltung von Benutzern.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    ///Aktualisiert die Benutzerdaten anhand der ID
    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
           user.setUserName(updatedUser.getUserName());
           user.setEmail(updatedUser.getEmail());
           user.setRole(updatedUser.getRole());
           return userRepository.save(user);
        });
    }
}
