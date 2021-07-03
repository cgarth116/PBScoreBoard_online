package ru.irbish.pbscoreboard.additionalMod;

import org.springframework.security.core.context.SecurityContextHolder;

public interface UserSessionParams {

    default String getUserNameSession(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
