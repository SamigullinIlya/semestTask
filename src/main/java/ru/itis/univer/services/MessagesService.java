package ru.itis.univer.services;

import ru.itis.univer.models.Message;
import ru.itis.univer.models.User;

import java.util.List;

public interface MessagesService {
    List<Message> getAllMessages(User user);

    void newMessage(Message message);
}
