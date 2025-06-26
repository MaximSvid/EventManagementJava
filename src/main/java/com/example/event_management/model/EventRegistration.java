package com.example.event_management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Diese Klasse repräsentiert die Tabelle "event_registrations",
 * die die Anmeldungen von Benutzern zu Veranstaltungen speichert.
 * Sie enthält Referenzen auf den Benutzer, das Event und das Anmeldedatum

 * * Die Getter- und Setter-Methoden ermöglichen den Zugriff und die Modifikation
 *  * der privaten Felder dieser Klasse.
 */
@Entity
@Table(name = "event_registrations")
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

    /// Getter- und Setter-Methoden für alle Felder
    /// Getter und Setter sind Methoden, mit denen man auf private Felder einer Klasse zugreifen und deren Werte ändern kann.
    /// Sie sorgen für Datenkapselung und kontrollierten Zugriff auf die Eigenschaften eines Objekts.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}


