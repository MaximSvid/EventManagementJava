package com.example.event_management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Diese Klasse repräsentiert die Tabelle "events" mit Informationen über Veranstaltungen,
 * wie Titel, Beschreibung, Datum, Ort, maximale Teilnehmerzahl und den Organisator.
 * Außerdem enthält sie die Liste der Anmeldungen zu diesem Event.
 */
@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private  String description;

    @Column(nullable = false)
    private LocalDateTime date;

    private String location;

    private Integer maxParticipants;

    @ManyToOne
    @JoinColumn(name = "organizerId", nullable = false)
    private User organizer;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventRegistration> registrations;
}
