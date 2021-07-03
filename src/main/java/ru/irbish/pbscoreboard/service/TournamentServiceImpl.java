package ru.irbish.pbscoreboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.irbish.pbscoreboard.models.Tournament;
import ru.irbish.pbscoreboard.repositories.TournamentRepository;

import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService{

    @Autowired
    private TournamentRepository tournamentRepository;

    public Tournament findByIdTournament(Long tournamentId){
        return tournamentRepository.findById(tournamentId).get();
    }

    @Override
    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    @Override
    public void delete(Tournament tournament) {
        tournamentRepository.delete(tournament);
    }

    @Override
    public Tournament findByName(String name) {
        return tournamentRepository.findByName(name);
    }

    @Override
    public void save(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

}
