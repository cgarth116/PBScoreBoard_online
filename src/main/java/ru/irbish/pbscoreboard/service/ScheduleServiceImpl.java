package ru.irbish.pbscoreboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.irbish.pbscoreboard.models.Schedule;
import ru.irbish.pbscoreboard.repositories.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> findByIdTournament(Long tournamentId){
        return scheduleRepository.findByIdTournament(tournamentId);
    }

    @Override
    public void delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    @Override
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    ;
}
