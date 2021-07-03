package ru.irbish.pbscoreboard.controllers.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.irbish.pbscoreboard.additionalMod.ErrorMessage;
import ru.irbish.pbscoreboard.models.Team;
import ru.irbish.pbscoreboard.additionalMod.ExternalModules;
import ru.irbish.pbscoreboard.additionalMod.ValidatorCheck;
import ru.irbish.pbscoreboard.service.TeamService;

@Controller
public class CreateTeamsController implements ExternalModules, ValidatorCheck, ErrorMessage {

    @Autowired
    private TeamService teamService;

    @Autowired
    private Team team;

    @GetMapping("/auth/createTeam")
    public String getCreateTeam(Model model){
        model.addAttribute("team",team);
        model.addAttribute("citys",getCityList());
        model.addAttribute("errorMessage", getErrorMessage());
        return "/auth/createTeam";
    }

    @PostMapping("/auth/createTeam")
    public  String addTeam(@ModelAttribute("team") Team team){

        team.setPoints(0);                                               // 0 points for default(change in future)
        if (!validatorCheck(team).isEmpty() || !saveTeam(team)){
            // in future reaction for validator and saveTeam can change.
            // use it
        }
        return "redirect:/auth/createTeam";
    }

    private boolean saveTeam(Team team) {
        Team teamFromDB = teamService.findByName(team.getName());
        setErrorMessage("", 2);
        if (teamFromDB != null && teamFromDB.getCity().equals(team.getCity())) {
            setErrorMessage("Команда уже в базе", 2);
            team.setName("");
            return false;
        }
        teamService.save(team);
        return true;
    }

}
