package ru.irbish.pbscoreboard.service;

import ru.irbish.pbscoreboard.models.User;

public interface UserService{
    User getByEmail(String email);
    void addUser(User user);
}
