package com.renan.projeto_bloco.infrastructure.persintence;

import com.renan.projeto_bloco.domain.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepositoryJpa extends JpaRepository<Tournament, Long> {
    
}
