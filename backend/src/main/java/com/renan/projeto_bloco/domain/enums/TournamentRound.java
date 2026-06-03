package com.renan.projeto_bloco.domain.enums;

public enum TournamentRound {
    GROUP("Group"),
    ROUNDOF32("RoundOf32"),
    ROUNDOF16("RoundOf16"),
    QUARTERFINALS("QuarterFinals"),
    SEMIFINALS("SemiFinals"),
    FINAL("Final");

    private final String description;

    TournamentRound(String description){
        this.description = description;
    }
}
