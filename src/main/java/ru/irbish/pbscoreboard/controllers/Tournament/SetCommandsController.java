package ru.irbish.pbscoreboard.controllers.Tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.irbish.pbscoreboard.additionalMod.AlgoritmSchedule;
import ru.irbish.pbscoreboard.additionalMod.ErrorMessage;
import ru.irbish.pbscoreboard.models.Team;
import ru.irbish.pbscoreboard.models.TeamsOfTournament;
import ru.irbish.pbscoreboard.models.Tournament;
import ru.irbish.pbscoreboard.service.TeamService;
import ru.irbish.pbscoreboard.service.TeamsOfTournamentService;
import ru.irbish.pbscoreboard.service.TournamentService;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Controller
public class SetCommandsController implements AlgoritmSchedule, ErrorMessage {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TeamsOfTournamentService teamsOfTournamentService;

    private Collection<Team> teamList = new LinkedList<>();
    private long tournamentId;

    @GetMapping("/auth/newTournament/setCommands")
    public String setCommands(Model model){
        List<Team> teams = teamService.findAll();
        long idTeam = 0;

        model.addAttribute("tournamentId", tournamentId);
        model.addAttribute("idTeam", idTeam);
        model.addAttribute("teams", teams);
        model.addAttribute("teamList",teamList);
        model.addAttribute("errorMessage",errorMessage[3]);

        return "/auth/newTournament/setCommands";
    }

    @GetMapping("/auth/newTournament/setCommands/{id}")
    public String setCommandToTournament(@PathVariable("id") long id){
        tournamentId = id;

        List<TeamsOfTournament> teamsOfTournaments = teamsOfTournamentService.findByIdTournament(tournamentId);
        teamList.clear();
        if(teamsOfTournaments.size() != 0){
            for(TeamsOfTournament team: teamsOfTournaments){
                teamList.add(teamService.findByName(team.getName()));
            }
        }

        return "redirect:/auth/newTournament/setCommands";
    }

    @PostMapping(value = "/auth/newTournament/setCommands")
    public  String addTeamToList2(long idTeam){
        if(idTeam != 0) {
            if (!teamList.stream().anyMatch(u -> u.getId() == idTeam)) {
                teamList.add(teamService.findById(idTeam));
            }
        }
        errorMessage[3] = "Выберите команду из списка";
        return "redirect:/auth/newTournament/setCommands";
    }

    @PostMapping(value = "/auth/newTournament/setCommands/{id}")
    public  String addTeamToList(@PathVariable("id") long id, long idTeam){
        if(idTeam != 0) {
            if (!teamList.stream().anyMatch(u -> u.getId() == idTeam)) {
                teamList.add(teamService.findById(idTeam));
            }
        }
        errorMessage[3] = "Выберите команду из списка";
        return "redirect:/auth/newTournament/setCommands";
    }

    @DeleteMapping("/auth/newTournament/setCommands/{idTeam}")
    public  String deleteTeamsFromList(@PathVariable("idTeam") long idTeam){
        teamList.remove(teamList.stream().filter(u -> u.getId() == idTeam).findFirst().get());

        return "redirect:/auth/newTournament/setCommands";
    }

    @PostMapping(value = "/auth/newTournament/backForName")
    public  String backForName(){
        return "redirect:/auth/newTournament/setName/" + tournamentId;
    }

    @PostMapping("/auth/newTournament/createTeamsOfTournament")
    public String createSchedule(){
        errorMessage[3] = "Выберите команду из списка";
        if(!teamList.isEmpty() &&
                teamList.size() > 2 &&
                teamList.size() != 14 &&
                teamList.size() != 19 &&
                teamList.size() < 25){

            List<TeamsOfTournament> teamsOfTournaments = teamsOfTournamentService.findByIdTournament(tournamentId);
            if(teamsOfTournaments.size() != 0){
                for(TeamsOfTournament team: teamsOfTournaments){
                    teamsOfTournamentService.delete(team);
                }
            }

            Tournament tournaments= tournamentService.findByIdTournament(tournamentId);
            int count = 0;
            int countTeam = teamList.size();
            for(Team team : teamList){
                TeamsOfTournament teamsOfTournament = new TeamsOfTournament();
                teamsOfTournament.setIdTournament(tournaments.getId());
                teamsOfTournament.setName(team.getName());
                teamsOfTournament.setGroupName(getGroupForTeam(countTeam,count++));
                teamsOfTournamentService.save(teamsOfTournament);
            }
            teamList.clear();
            return "redirect:/auth/tournamentsList";
        }
        errorMessage[3] = "Кол-во команд должно быть от 3 до 24(кроме 14 и 19)";
        return "redirect:/auth/newTournament/setCommands";
    }

}
