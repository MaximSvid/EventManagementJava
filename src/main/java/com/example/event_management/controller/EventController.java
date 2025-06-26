package com.example.event_management.controller;

import com.example.event_management.model.Event;
import com.example.event_management.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Dieser Controller stellt REST-Endpunkte für die Verwaltung von Events bereit.
 */
@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    /// GET /api/events - Alle Events abrufen
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
    /// GET /api/events/{id} - Event-Details abrufen
    @GetMapping
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    /// POST /api/events - Neues Event erstellen
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }
}
