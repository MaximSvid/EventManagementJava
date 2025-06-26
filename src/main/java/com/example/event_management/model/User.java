package com.example.event_management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Die Tabelle „users“ enthält folgende Spalten:
 * id (Primärschlüssel), userName, email, sowie role (Benutzerrolle: ORGANIZER oder PARTICIPANT).
 * Jeder Benutzer kann mehrere Events organisieren und an mehreren Events
 * Die Getter- und Setter-Methoden ermöglichen den Zugriff und die Modifikation
 * der privaten Felder dieser Klasse.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private List<Event> organizedEvents;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EventRegistration> registrations;

    public enum Role {
        ORGANIZER, PARTICIPANT
    }

    /// Getter- und Setter-Methoden für alle Felder
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Event> getOrganizedEvents() {
        return organizedEvents;
    }

    public void setOrganizedEvents(List<Event> organizedEvents) {
        this.organizedEvents = organizedEvents;
    }

    public List<EventRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<EventRegistration> registrations) {
        this.registrations = registrations;
    }
}