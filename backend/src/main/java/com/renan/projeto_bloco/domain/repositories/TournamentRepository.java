package com.renan.projeto_bloco.domain.repositories;

import com.renan.projeto_bloco.application.dto.TournamentResponseDTO;
import com.renan.projeto_bloco.domain.enums.TournamentStatus;
import com.renan.projeto_bloco.domain.models.Tournament;
import com.renan.projeto_bloco.domain.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TournamentRepository {
    List<Tournament> findByStatus(TournamentStatus status);
    List<Tournament> findByOrganizer(User organizer);

    @Query("SELECT t FROM Tournament t WHERE t.organizer = :user OR :user MEMBER OF t.participants")
    List<Tournament> findTournamentsByUser(@Param("user") User user);

    Optional<Tournament> findById(Long tournamentId);

    Tournament save(Tournament tournament);

    List<Tournament> findAll();
}
