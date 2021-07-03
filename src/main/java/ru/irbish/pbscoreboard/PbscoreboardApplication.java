package ru.irbish.pbscoreboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.irbish.pbscoreboard.models.Team;
import ru.irbish.pbscoreboard.models.Tournament;

@SpringBootApplication
public class PbscoreboardApplication {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Team team(){
        return new Team();
    }

    @Bean
    public Tournament tournament(){
        return new Tournament();
    } 

    public static void main(String[] args) {
        SpringApplication.run(PbscoreboardApplication.class, args);
    }

}
