package ru.irbish.pbscoreboard.controllers.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.irbish.pbscoreboard.models.User;
import ru.irbish.pbscoreboard.additionalMod.ValidatorCheck;
import ru.irbish.pbscoreboard.service.UserService;

@Controller
public class SignUpController implements WebMvcConfigurer, ValidatorCheck {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/authorization").setViewName("authorization");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signUp")
    public String  getSignUpPage(){
        return "signUp";
    }

    @PostMapping("/signUp")
    public  String signUpUser(@ModelAttribute("user") User user){
        if (!validatorCheck(user).isEmpty() || !saveUser(user)){
            return "signUp";
        }
        return "redirect:/authorization";
    }

    public boolean saveUser(User user) {
        User userFromDB = userService.getByEmail(user.getEmail());
        if (userFromDB != null) {
            return false;
        }

        //user.setRoles(new Role("USER",user.getRoles())); //todo any Role in future
        user.setHashPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return true;
    }

}
