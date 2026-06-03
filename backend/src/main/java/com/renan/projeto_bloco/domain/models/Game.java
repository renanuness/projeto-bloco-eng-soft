package com.renan.projeto_bloco.domain.models;

import jakarta.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="match_id", nullable = false)
    private Match match;

    private short pointsPlayerOne;
    private short pointsPlayerTwo;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
