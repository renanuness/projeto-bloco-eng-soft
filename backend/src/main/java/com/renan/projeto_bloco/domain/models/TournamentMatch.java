package com.renan.projeto_bloco.domain.models;

import com.renan.projeto_bloco.domain.enums.TournamentRound;
import jakarta.persistence.*;

@Entity
public class TournamentMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="match)id", referencedColumnName = "id")
    private Match match;

    private TournamentRound round;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
