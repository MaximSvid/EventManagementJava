package com.example.event_management.service;

import com.example.event_management.model.EventRegistration;
import com.example.event_management.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Dieser Service bietet Methoden zur Verwaltung von Event-Anmeldungen.
 */

@Service
public class EventRegistrationService {
    @Autowired
    private EventRegistrationRepository registrationRepository;

    public List<EventRegistration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Optional<EventRegistration> getRegistrationById(Long id) {
        return registrationRepository.findById(id);
    }

    public List<EventRegistration> getRegistrationsByUserId(Long userId) {
        return registrationRepository.findByUserId(userId);
    }


    public List<EventRegistration> getRegistrationsByEventId(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    public EventRegistration createRegistration(EventRegistration registration) {
        return registrationRepository.save(registration);
    }

    /// Aktualisiert die Anmeldedaten anhand der ID
    public Optional<EventRegistration> updateRegistration(Long id, EventRegistration updatedRegistration) {
        return registrationRepository.findById(id).map(registration -> {
            registration.setUser(updatedRegistration.getUser());
            registration.setEvent(updatedRegistration.getEvent());
            registration.setRegistrationDate(updatedRegistration.getRegistrationDate());
            return registrationRepository.save(registration);
        });
    }

    public boolean deleteRegistration(Long id) {
        if (registrationRepository.existsById(id)) {
            registrationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}



