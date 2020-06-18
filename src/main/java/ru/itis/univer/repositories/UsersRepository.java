package ru.itis.univer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.univer.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
