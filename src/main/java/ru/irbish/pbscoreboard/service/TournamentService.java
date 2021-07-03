package ru.irbish.pbscoreboard.service;

import ru.irbish.pbscoreboard.models.Tournament;

import java.util.List;

public interface TournamentService {
    Tournament findByIdTournament(Long tournamentId);
    List<Tournament> findAll();
    void delete(Tournament tournament);

    Tournament findByName(String name);

    void save(Tournament tournament);
}
