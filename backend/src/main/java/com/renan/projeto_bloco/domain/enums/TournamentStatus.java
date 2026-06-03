package com.renan.projeto_bloco.domain.enums;

public enum TournamentStatus {
    OPEN("Aberto"),
    CLOSED("Fechado"),
    IN_PROGRESS("Em Andamento"),
    FINISHED("Finalizado"),
    CANCELLED("Cancelado");

    private final String description;

    TournamentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

