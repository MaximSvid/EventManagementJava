package com.example.event_management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Diese Klasse repräsentiert die Tabelle "event_registrations",
 * die die Anmeldungen von Benutzern zu Veranstaltungen speichert.
 * Sie enthält Referenzen auf den Benutzer, das Event und das Anmeldedatum
 */
@Entity
@Table(name = "event_registrations")
@Data
public class EventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "eventId", nullable = false)
    private Event event;

    @Column(nullable = false)
    private LocalDateTime registrationDate;
}
