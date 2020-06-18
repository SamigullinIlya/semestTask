package ru.itis.univer.websockets.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.univer.dto.MessageDto;
import ru.itis.univer.models.Message;
import ru.itis.univer.models.User;
import ru.itis.univer.models.enums.Role;
import ru.itis.univer.services.MessagesService;
import ru.itis.univer.services.UsersService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<Long, List<WebSocketSession>> sessions = new HashMap<>();

    private final ObjectMapper objectMapper;
    private final MessagesService messagesService;
    private final UsersService usersService;

    public WebSocketMessagesHandler(ObjectMapper objectMapper, MessagesService messagesService, UsersService usersService) {
        this.objectMapper = objectMapper;
        this.messagesService = messagesService;
        this.usersService = usersService;
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage) throws Exception {
        String messageText = (String) webSocketMessage.getPayload();
        MessageDto messageDto = objectMapper.readValue(messageText, MessageDto.class);

        User owner = usersService.getOne(messageDto.getOwner());
        User receiver = usersService.getOne(messageDto.getReceiver());

        messagesService.newMessage(Message.builder()
                .content(messageDto.getContent())
                .dateOfSending(LocalDateTime.now())
                .owner(owner)
                .receiver(receiver)
                .build());

        String textMessage = objectMapper.writeValueAsString(messageDto);

        Long id = messageDto.getReceiver();
        if (owner.getRole().equals(Role.ADMIN)) {
            id = messageDto.getOwner();
        }
        loadSession(id, session);

        sessions.get(id).forEach(webSocketSession -> {
            try {
                webSocketSession.sendMessage(new TextMessage(textMessage));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    private void loadSession(Long id, WebSocketSession session) {
        List<WebSocketSession> currentSession;
        if ((currentSession = sessions.get(id)) != null) {
            currentSession.add(session);
        } else {
            currentSession = new ArrayList<>();
            currentSession.add(session);
            sessions.put(id, currentSession);
        }
    }
}
