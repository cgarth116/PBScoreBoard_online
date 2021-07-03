package ru.irbish.pbscoreboard.controllers.Tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.irbish.pbscoreboard.additionalMod.AlgoritmSchedule;
import ru.irbish.pbscoreboard.additionalMod.UserSessionParams;
import ru.irbish.pbscoreboard.models.Schedule;
import ru.irbish.pbscoreboard.models.TeamsOfTournament;
import ru.irbish.pbscoreboard.models.Tournament;
import ru.irbish.pbscoreboard.service.ScheduleService;
import ru.irbish.pbscoreboard.service.TeamsOfTournamentService;
import ru.irbish.pbscoreboard.service.TournamentService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class TournamentsListController implements UserSessionParams, AlgoritmSchedule {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TeamsOfTournamentService teamsOfTournamentService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/auth/tournamentsList")
    public String getTournamentsList(Model model){
        String owner = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Tournament> tournamentList = tournamentService.findAll().
                stream().
                filter(o -> o.getOwner().equals(owner)).
                collect(Collectors.toList());
        Collections.sort(tournamentList,Comparator.comparing(Tournament::getDate));
        model.addAttribute("tournamentList", tournamentList);

        return "/auth/tournamentsList";
    }

    @PostMapping("/auth/tournamentsList/edit/{id}")
    public  String editTournamentList(@PathVariable("id") long id){
        return "redirect:/auth/newTournament/setName/" + id;
    }

    @DeleteMapping("/auth/tournamentsList/delete/{id}")
    public  String deleteTournament(@PathVariable("id") long id){               //удаляем во всех базах все связанное с турниром


        tournamentService.delete(tournamentService.findByIdTournament(id));
        for(TeamsOfTournament team: teamsOfTournamentService.findByIdTournament(id)){
            teamsOfTournamentService.delete(team);
        }
        for(Schedule schedule: scheduleService.findByIdTournament(id)){
            scheduleService.delete(schedule);
        }

        return "redirect:/auth/tournamentsList";
    }

    @PostMapping("/auth/tournamentsList/schedule/{id}")
    public  String scheduleTournament(@PathVariable("id") long id){

        List<Schedule> teamsOfTournaments = scheduleService.findByIdTournament(id);
        if(teamsOfTournaments.size() != 0){
            for(Schedule schedule: teamsOfTournaments){
                scheduleService.delete(schedule);
            }
        }

        List<TeamsOfTournament> tournamentList = teamsOfTournamentService.findByIdTournament(id);
        int count = 0;
        int countTeams = tournamentList.size();

        int countMax = getCountMatch(countTeams);
        while(count < countMax){
            Schedule schedule = new Schedule();
            schedule.setIdTournament(id);
            schedule.setTeamA(tournamentList.get(getIdTeam(countTeams,count++)).getName());
            schedule.setTeamB(tournamentList.get(getIdTeam(countTeams,count++)).getName());
            schedule.setIdMatch((long) (count/2));
            schedule.setTime(null);
            scheduleService.save(schedule);
        }
        return "redirect:/auth/schedule/{id}";
    }
}
