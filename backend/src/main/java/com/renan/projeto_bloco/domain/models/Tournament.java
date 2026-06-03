package com.renan.projeto_bloco.domain.models;

import com.renan.projeto_bloco.domain.enums.TournamentStatus;
import com.renan.projeto_bloco.domain.exceptions.DomainException;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tournaments")
@Getter
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 200)
    private String place;

    @Column(name = "max_participants", nullable = false)
    private int maxParticipants;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TournamentStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
        name = "tournament_matches",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "user_ud")
    )
    Set<User> participants;

    public Tournament(User organizer,
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
                      int maxParticipants) {
        this.organizer = organizer;
        this.name = name;
        this.maxParticipants = maxParticipants;
        this.date = date;
        this.place = place;
    }

    public Tournament() {

    }

    public void addParticipant(User user) throws DomainException {
        if (this.participants.size() >= this.maxParticipants) {
            throw new DomainException("Torneio já atingiu o número máximo de participantes");
        }
        if (this.status != TournamentStatus.OPEN) {
            throw new DomainException("Torneio não está aberto para inscrições");
        }
        if (user.equals(this.organizer)) {
            throw new DomainException("Organizador não pode participar do próprio torneio");
        }

        this.participants.add(user);
    }

    public int getCurrentParticipants() {
        return this.participants.size();
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
