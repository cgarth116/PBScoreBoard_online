package ru.irbish.pbscoreboard.service;

import ru.irbish.pbscoreboard.models.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findByIdTournament(Long tournamentId);
    void delete(Schedule schedule);
    void save(Schedule schedule);
}
