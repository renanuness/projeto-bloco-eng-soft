package com.renan.projeto_bloco.application.dto;

import com.renan.projeto_bloco.domain.models.Tournament;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TournamentResponseDTO(
        Long id,
        String name,
        LocalDate date,
        String place,
        int maxParticipants,
        int currentParticipants,
        String status,
        String organizerName,
        LocalDateTime createdAt
) {
    public static TournamentResponseDTO from(Tournament tournament) {
        return new TournamentResponseDTO(
                tournament.getId(),
                tournament.getName(),
                tournament.getDate(),
                tournament.getPlace(),
                tournament.getMaxParticipants(),
                tournament.getCurrentParticipants(),
                tournament.getStatus().getDescription(),
                tournament.getOrganizer().getName(),
                tournament.getCreatedAt()
        );
    }
}
