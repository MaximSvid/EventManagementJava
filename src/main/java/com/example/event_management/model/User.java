package com.example.event_management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// Die Tabelle „users“ enthält folgende Spalten:
//id (Primärschlüssel), userName, email, sowie role (Benutzerrolle: ORGANIZER oder PARTICIPANT).
//Jeder Benutzer kann mehrere Events organisieren und an mehreren Events teilnehmen
@Entity
@Table(name = "users")
@Data
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
}
