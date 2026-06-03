package com.renan.projeto_bloco.infrastructure.persintence;

import com.renan.projeto_bloco.domain.models.User;
import com.renan.projeto_bloco.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryJpa jpaRepository;

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmal(String email) {
        return jpaRepository.findByEmail(email);
    }

    @Override
    public User save(User usuario) {
        return jpaRepository.save(usuario);
    }
}
