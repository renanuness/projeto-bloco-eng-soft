package com.renan.projeto_bloco.application.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record TournamentRequestDTO(
        @NotBlank(message = "Nome do torneio é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String name,

        @NotNull(message = "Data é obrigatória")
        @Future(message = "Data deve ser no futuro")
        LocalDate date,

        @NotBlank(message = "Local é obrigatório")
        @Size(max = 200, message = "Local deve ter no máximo 200 caracteres")
        String place,

        @Min(value = 2, message = "Mínimo de 2 participantes")
        @Max(value = 128, message = "Máximo de 128 participantes")
        int maxParticipants,

        Long organizerId
) {}
