package com.renan.projeto_bloco.infrastructure.persintence;

import com.renan.projeto_bloco.application.dto.TournamentResponseDTO;
import com.renan.projeto_bloco.domain.enums.TournamentStatus;
import com.renan.projeto_bloco.domain.models.Tournament;
import com.renan.projeto_bloco.domain.models.User;
import com.renan.projeto_bloco.domain.repositories.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TournamentRepositoryImpl implements TournamentRepository {
    private final TournamentRepositoryJpa jpaRepository;

    @Override
    public List<Tournament> findByStatus(TournamentStatus status) {
        return List.of();
    }

    @Override
    public List<Tournament> findByOrganizer(User organizer) {
        return List.of();
    }

    @Override
    public List<Tournament> findTournamentsByUser(User user) {
        return List.of();
    }

    @Override
    public Optional<Tournament> findById(Long tournamentId) {
        return Optional.empty();
    }

    @Override
    public Tournament save(Tournament tournament) {
        return null;
    }

    @Override
    public List<Tournament> findAll() {
        return jpaRepository.findAll();
    }
}
