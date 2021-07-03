package ru.irbish.pbscoreboard.controllers.authorization;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {

    @GetMapping("/authorization")
    public String getSignInPage(){
        return "authorization";
    }
}

