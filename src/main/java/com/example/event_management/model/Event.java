package com.example.event_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Diese Klasse repräsentiert die Tabelle "events" mit Informationen über Veranstaltungen,
 * wie Titel, Beschreibung, Datum, Ort, maximale Teilnehmerzahl und den Organisator.
 * Außerdem enthält sie die Liste der Anmeldungen zu diesem Event.

 * Die Getter- und Setter-Methoden ermöglichen den Zugriff und die Modifikation
 * der privaten Felder dieser Klasse.
 */
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    private String location;

    private Integer maxParticipants;

    @ManyToOne
    @JoinColumn(name = "organizerId", nullable = false)
    private User organizer;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventRegistration> registrations;

    /// Getter- und Setter-Methoden für alle Felder
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public List<EventRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<EventRegistration> registrations) {
        this.registrations = registrations;
    }
}
