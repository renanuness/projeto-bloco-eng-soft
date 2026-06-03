package com.renan.projeto_bloco.domain.repositories;

import com.renan.projeto_bloco.domain.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public interface UserRepository {
    
    boolean existsByEmail(@NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);

    User save(User usuario);
}
