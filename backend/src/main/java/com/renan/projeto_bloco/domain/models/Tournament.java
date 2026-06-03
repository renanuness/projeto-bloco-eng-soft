package com.renan.projeto_bloco.domain.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    private String name;
    private LocalDate date;
    private String place;
    private int maxParticipants;

    @ManyToMany
    @JoinTable(
        name = "tournament_matches",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "user_ud")
    )
    Set<User> participants;

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
