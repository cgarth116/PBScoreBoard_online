package ru.irbish.pbscoreboard.controllers.Tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.irbish.pbscoreboard.additionalMod.ErrorMessage;
import ru.irbish.pbscoreboard.models.Tournament;
import ru.irbish.pbscoreboard.additionalMod.UserSessionParams;
import ru.irbish.pbscoreboard.additionalMod.ValidatorCheck;
import ru.irbish.pbscoreboard.service.TournamentService;

import java.sql.Date;

@Controller
public class SetNameController implements UserSessionParams, ValidatorCheck, ErrorMessage {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private  Tournament tournament;

    private long idTournament;

    @GetMapping("/auth/newTournament/setName")
    public String setTournamentName(Model model){
        if (idTournament == 0){
            tournament.setDate(new Date(System.currentTimeMillis()).toString());
        }
        model.addAttribute("tournament",tournament);
        model.addAttribute("errorMessage",getErrorMessage());
        return "/auth/newTournament/setName";
    }

    @GetMapping("/auth/newTournament/setName/{id}")
    public String setTournamentNameForId(@PathVariable("id") long id){
        if (id == 0){
            idTournament = 0;
            tournament.setName("");
        } else {
            idTournament = id;
            tournament.setName(tournamentService.findByIdTournament(id).getName());
            tournament.setDate(tournamentService.findByIdTournament(id).getDate());
        }

        return "redirect:/auth/newTournament/setName";
    }

    @PostMapping(value = "/auth/newTournament/setName")
    public  String setTournamentName(Tournament tournament){
        setErrorMessage("", 0);
        setErrorMessage("", 0);
        if (validatorCheck(tournament).isEmpty() &&
                (tournamentService.findByName(tournament.getName()) == null ||
                        (idTournament != 0 && tournamentService.findByIdTournament(idTournament).getName().equals(tournament.getName())))) {
            String dateTmp = tournament.getDate();                  //запоминаем дату турнира до изменения
            if (idTournament != 0) {
                String nameTmp = tournament.getName();                  //запоминаем название турнира
                tournament = tournamentService.findByIdTournament(idTournament);
                tournament.setName(nameTmp);
            }
            tournament.setDate(dateTmp);
            tournament.setOwner(getUserNameSession());
            tournamentService.save(tournament);              //создаем(меняем) запись турнира
            idTournament = 0;                                   //set default id for create new tournament
            return "redirect:/auth/newTournament/setCommands/" + tournamentService.findByName(tournament.getName()).getId();
        }
        if(idTournament == 0 && tournamentService.findByName(tournament.getName()) != null){
            setErrorMessage("Это название уже используется",0);
        }
        if (!validatorCheck(tournament).isEmpty()) {
            setErrorMessage(validatorCheck(tournament).stream().findFirst().get().getMessage(), 1);
        }

        return "redirect:/auth/newTournament/setName/" + idTournament;
    }

}

