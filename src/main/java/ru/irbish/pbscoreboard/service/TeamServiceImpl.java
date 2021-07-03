package ru.irbish.pbscoreboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.irbish.pbscoreboard.models.Team;
import ru.irbish.pbscoreboard.repositories.TeamsRepository;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamsRepository teamsRepository;

    @Override
    public Team findByName(String name) {
        return teamsRepository.findByName(name);
    }

    @Override
    public Team findById(Long id) {
        return teamsRepository.findById(id).get();
    }

    @Override
    public void save(Team team) {
        teamsRepository.save(team);
    }

    @Override
    public List<Team> findAll() {
        return teamsRepository.findAll();
    }
}
