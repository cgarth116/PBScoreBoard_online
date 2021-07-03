package ru.irbish.pbscoreboard.service;

import ru.irbish.pbscoreboard.models.Team;
import ru.irbish.pbscoreboard.models.TeamsOfTournament;

import java.util.List;

public interface TeamsOfTournamentService {
    List<TeamsOfTournament> findByIdTournament(Long tournamentId);
    void delete(TeamsOfTournament team);
    void save(TeamsOfTournament teamsOfTournament);
}
