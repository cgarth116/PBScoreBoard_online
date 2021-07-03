package ru.irbish.pbscoreboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.irbish.pbscoreboard.models.Schedule;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByIdTournament(Long idTournament);
}
