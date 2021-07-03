package ru.irbish.pbscoreboard.service;

import ru.irbish.pbscoreboard.models.Team;

import java.util.List;

public interface TeamService {
    Team findByName(String name);
    Team findById(Long id);
    void save(Team team);
    List<Team> findAll();
}
