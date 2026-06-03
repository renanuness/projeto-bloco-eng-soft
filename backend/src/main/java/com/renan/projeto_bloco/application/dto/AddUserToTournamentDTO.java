package com.renan.projeto_bloco.application.dto;

public record AddUserToTournamentDTO(
        Long tournamentId,
        Long organizerId,
        Long userId
) {
}
