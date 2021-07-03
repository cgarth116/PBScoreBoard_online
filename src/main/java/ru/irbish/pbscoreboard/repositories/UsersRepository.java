package ru.irbish.pbscoreboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.irbish.pbscoreboard.models.User;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
