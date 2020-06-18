package ru.itis.univer.services.impls;

import org.springframework.stereotype.Service;
import ru.itis.univer.models.Message;
import ru.itis.univer.models.User;
import ru.itis.univer.repositories.MessagesRepository;
import ru.itis.univer.services.MessagesService;

import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService {

    private final MessagesRepository messagesRepository;

    public MessagesServiceImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<Message> getAllMessages(User user) {
        return messagesRepository.findAllByOwnerOrReceiver(user, user);
    }

    @Override
    public void newMessage(Message message) {
        messagesRepository.save(message);
    }
}
