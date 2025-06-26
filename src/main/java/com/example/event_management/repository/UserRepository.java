package com.example.event_management.repository;

import com.example.event_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Dieses Interface dient als Repository für die Benutzerverwaltung.
 * Es ermöglicht CRUD-Operationen auf der User-Entität in der Datenbank.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
