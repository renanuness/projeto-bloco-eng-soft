package com.renan.projeto_bloco.presentation.controllers;

import com.renan.projeto_bloco.application.dto.AddUserToTournamentDTO;
import com.renan.projeto_bloco.application.dto.TournamentRequestDTO;
import com.renan.projeto_bloco.application.dto.TournamentResponseDTO;
import com.renan.projeto_bloco.domain.exceptions.DomainException;
import com.renan.projeto_bloco.domain.services.TournamentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// controller/TournamentController.java
@RestController
@RequestMapping("/api/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    @PostMapping
    public ResponseEntity<TournamentResponseDTO> create(
            @Valid @RequestBody TournamentRequestDTO dto) {
        try {
            TournamentResponseDTO response = tournamentService.create(dto);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response.id())
                    .toUri();

            return ResponseEntity.created(location).body(response);
        }catch (DomainException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TournamentResponseDTO>> findAll(
            @RequestParam(required = false) String status) {

        if ("OPEN".equalsIgnoreCase(status)) {
            return ResponseEntity.ok(tournamentService.findAllOpen());
        }

        return ResponseEntity.ok(tournamentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentResponseDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tournamentService.findById(id));
        } catch (DomainException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/addParticipants")
    public ResponseEntity<Void> addParticipant(
            @RequestBody AddUserToTournamentDTO dto) {
        try {
            tournamentService.addParticipant(dto);

            return ResponseEntity.noContent().build();
        }catch (DomainException e){
            return ResponseEntity.badRequest().build();
        }
    }
}