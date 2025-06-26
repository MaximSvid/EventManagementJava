package com.example.event_management.controller;

import com.example.event_management.model.Event;
import com.example.event_management.model.EventRegistration;
import com.example.event_management.model.User;
import com.example.event_management.service.EventRegistrationService;
import com.example.event_management.service.EventService;
import com.example.event_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Dieser Controller stellt REST-Endpunkte für die Verwaltung von Event-Anmeldungen bereit.
 */
@RestController
@RequestMapping("/api")
public class EventRegistrationController {
    @Autowired
    private EventRegistrationService registrationService;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    /// POST /api/events/{eventId}/register - Registrierung für ein Event
    @PostMapping("/events/{eventId}/register")
    public ResponseEntity<EventRegistration> registerForEvent(@PathVariable Long eventId, @RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        User user = userService.getUserById(userId);
        Event event = eventService.getEventById(eventId);

        EventRegistration registration = new EventRegistration();
        registration.setUser(user);
        registration.setEvent(event);
        registration.setRegistrationDate(LocalDateTime.now());

        return ResponseEntity.ok(registrationService.createRegistration(registration));
    }
}
