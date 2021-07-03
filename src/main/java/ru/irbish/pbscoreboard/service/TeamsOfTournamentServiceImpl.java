package ru.irbish.pbscoreboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.irbish.pbscoreboard.models.Team;
import ru.irbish.pbscoreboard.models.TeamsOfTournament;
import ru.irbish.pbscoreboard.repositories.TeamsOfTournamentRepository;

import java.util.List;

@Service
public class TeamsOfTournamentServiceImpl implements TeamsOfTournamentService{

    @Autowired
    private TeamsOfTournamentRepository teamsOfTournamentRepository;

    @Override
    public List<TeamsOfTournament> findByIdTournament(Long tournamentId) {
        return teamsOfTournamentRepository.findByIdTournament(tournamentId);
    }

    @Override
    public void delete(TeamsOfTournament team) {
        teamsOfTournamentRepository.delete(team);
    }

    @Override
    public void save(TeamsOfTournament teamsOfTournament) {
        teamsOfTournamentRepository.save(teamsOfTournament);
    }
}
