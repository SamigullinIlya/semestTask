package ru.itis.univer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.univer.models.Message;
import ru.itis.univer.models.User;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByOwnerOrReceiver(User owner, User receiver);
}
