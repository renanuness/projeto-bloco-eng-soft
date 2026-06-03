package com.renan.projeto_bloco.domain.services;

import com.renan.projeto_bloco.application.dto.AddUserToTournamentDTO;
import com.renan.projeto_bloco.application.dto.TournamentRequestDTO;
import com.renan.projeto_bloco.application.dto.TournamentResponseDTO;
import com.renan.projeto_bloco.domain.enums.TournamentStatus;
import com.renan.projeto_bloco.domain.exceptions.DomainException;
import com.renan.projeto_bloco.domain.models.Tournament;
import com.renan.projeto_bloco.domain.models.User;
import com.renan.projeto_bloco.domain.repositories.TournamentRepository;
import com.renan.projeto_bloco.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// service/TournamentService.java
@Service
@RequiredArgsConstructor
@Transactional
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final UserRepository userRepository;

    public TournamentResponseDTO create(TournamentRequestDTO dto) throws DomainException {
        User organizer = userRepository.findById(dto.organizerId())
                .orElseThrow(() -> new DomainException("Organizador não encontrado"));

        Tournament tournament = new Tournament(
                organizer,
                dto.name(),
                dto.date(),
                dto.place(),
                dto.maxParticipants()
        );

        tournament = tournamentRepository.save(tournament);
        return TournamentResponseDTO.from(tournament);
    }

    public TournamentResponseDTO findById(Long id) throws DomainException {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new DomainException("Torneio não encontrado"));
        return TournamentResponseDTO.from(tournament);
    }

    public List<TournamentResponseDTO> findAllOpen() {
        return tournamentRepository.findByStatus(TournamentStatus.OPEN)
                .stream()
                .map(TournamentResponseDTO::from)
                .toList();
    }

    public List<TournamentResponseDTO> findByOrganizer(Long organizerId) throws DomainException {
        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new DomainException("Organizador não encontrado"));

        return tournamentRepository.findByOrganizer(organizer)
                .stream()
                .map(TournamentResponseDTO::from)
                .toList();
    }

    public void addParticipant(AddUserToTournamentDTO dto) throws DomainException {
        Tournament tournament = tournamentRepository.findById(dto.tournamentId())
                .orElseThrow(() -> new DomainException("Torneio não encontrado"));

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new DomainException("Usuário não encontrado"));

        tournament.addParticipant(user);
        tournamentRepository.save(tournament);

    }

    public List<TournamentResponseDTO> findAll() {
        return tournamentRepository.findAll();
    }
}