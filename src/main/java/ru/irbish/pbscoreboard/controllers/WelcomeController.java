package ru.irbish.pbscoreboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.irbish.pbscoreboard.additionalMod.UserSessionParams;

@Controller
public class WelcomeController implements UserSessionParams {

    @GetMapping("/")
    public String welcomePage(){
        return "welcome";
    }

    @GetMapping("/welcome")
    public String welcomePageNoAuth(){
        return "welcome";
    }

}

