package ru.irbish.pbscoreboard.controllers.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import ru.irbish.pbscoreboard.models.Schedule;

import ru.irbish.pbscoreboard.models.SchedulePDFExporter;
import ru.irbish.pbscoreboard.models.TeamsOfTournament;
import ru.irbish.pbscoreboard.service.ScheduleService;
import ru.irbish.pbscoreboard.service.TeamsOfTournamentService;
import ru.irbish.pbscoreboard.service.TournamentService;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.List;


@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TeamsOfTournamentService teamsOfTournamentService;

    @Autowired
    private TournamentService tournamentService;

    Long tournamentId;

    @GetMapping("/auth/schedule")
    public String schedule(Model model){
        List<Schedule> schedules= scheduleService.findByIdTournament(tournamentId);
        List<TeamsOfTournament> teamsOfTournaments = teamsOfTournamentService.findByIdTournament(tournamentId);
        String tournamentName = "";
        if (tournamentId != null){
            tournamentName = tournamentService.findByIdTournament(tournamentId).getName();
        }
        SortedSet<Character> setGroups = new TreeSet<>();
        for(TeamsOfTournament team: teamsOfTournaments){
            setGroups.add(team.getGroupName());
        }
        model.addAttribute("schedules", schedules);
        model.addAttribute("groups", setGroups.toArray());
        model.addAttribute("teams" ,teamsOfTournaments);
        model.addAttribute("tournamentName", tournamentName);

        return "/auth/schedule";
    }

    @GetMapping("/auth/schedule/{id}")
    public String schedule(@PathVariable("id") long id){
        tournamentId = id;
        return "redirect:/auth/schedule";
    }

    @PostMapping("/auth/schedule")
    public void pdfSchedule(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=schedule.pdf";
            response.setHeader(headerKey, headerValue);

            List<Schedule> scheduleList = Arrays.asList(new Schedule());
            String tournamentName = null;
            if (tournamentId != null) {
                tournamentName = tournamentService.findByIdTournament(tournamentId).getName();
                scheduleList = scheduleService.findByIdTournament(tournamentId);
            }
            SchedulePDFExporter exporter = new SchedulePDFExporter(scheduleList, tournamentName);
            exporter.export(response);
        } catch(Exception e){
            System.out.println("Что то с pdf пошло не так.");
        }
    }

}

