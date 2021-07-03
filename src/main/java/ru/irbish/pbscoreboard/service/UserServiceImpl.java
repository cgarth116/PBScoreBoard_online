package ru.irbish.pbscoreboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.irbish.pbscoreboard.models.User;
import ru.irbish.pbscoreboard.repositories.UsersRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User getByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public void addUser(User user) {
        usersRepository.save(user);
    }
}
