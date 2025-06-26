package com.example.event_management.repository;

import com.example.event_management.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Dieses Interface dient als Repository für die Verwaltung von Event-Anmeldungen.
 * Es ermöglicht CRUD-Operationen auf der EventRegistration-Entität und bietet Methoden,
 * um Anmeldungen nach Benutzer oder Event zu finden
 */
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    List<EventRegistration> findByUserId(Long userId);
    List<EventRegistration> findByEventId(Long eventId);
}
