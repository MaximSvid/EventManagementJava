package com.example.event_management.repository;

import com.example.event_management.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Dieses Interface dient als Repository für die Verwaltung von Events.
 * Es ermöglicht CRUD-Operationen auf der Event-Entität und bietet eine Methode,
 * um alle Events eines bestimmten Organisators zu finden.
 */
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizerId(Long organizerId);
}
