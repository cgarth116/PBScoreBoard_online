package ru.irbish.pbscoreboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.irbish.pbscoreboard.models.Tournament;

import java.util.Optional;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Tournament findByName(String name);
    Tournament findById(long id);
}