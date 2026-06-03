package com.renan.projeto_bloco.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO (
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        String password)
{}
