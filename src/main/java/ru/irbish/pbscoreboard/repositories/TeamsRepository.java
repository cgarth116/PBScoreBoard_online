package ru.irbish.pbscoreboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.irbish.pbscoreboard.models.Team;

public interface TeamsRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);
    Team findById(long id);
}
