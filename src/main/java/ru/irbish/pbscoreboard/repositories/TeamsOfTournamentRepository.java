package ru.irbish.pbscoreboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.irbish.pbscoreboard.models.TeamsOfTournament;

import java.util.List;

public interface TeamsOfTournamentRepository extends JpaRepository<TeamsOfTournament, Long> {
    List<TeamsOfTournament> findByIdTournament(Long idTournament);
}
