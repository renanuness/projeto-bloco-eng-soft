package com.renan.projeto_bloco.application.dto;

import com.renan.projeto_bloco.domain.models.User;

public record UserResponseDTO(
        Long id,
        String nome,
        String email
) {
    public static UserResponseDTO fromDomain(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
