package com.renan.projeto_bloco.domain.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Match {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="player_one_id", nullable = false)
    private User playerOne;

    @ManyToOne
    @JoinColumn(name="player_two_id", nullable = false)
    private User playerTwo;

    @OneToMany(mappedBy = "match")
    private List<Game> games = new ArrayList<>();

    private Long winnerId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
