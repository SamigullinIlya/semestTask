package ru.itis.univer.websockets.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.itis.univer.websockets.handlers.AuthHandshakeHandler;
import ru.itis.univer.websockets.handlers.WebSocketMessagesHandler;

@Configuration
public class WebsocketConfig implements WebSocketConfigurer {

    private final WebSocketMessagesHandler handler;
    private final AuthHandshakeHandler handShakeHandler;

    public WebsocketConfig(WebSocketMessagesHandler handler,
                           AuthHandshakeHandler handShakeHandler) {
        this.handler = handler;
        this.handShakeHandler = handShakeHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(handler, "/chatting")
                .setHandshakeHandler(handShakeHandler)
                .withSockJS();
    }
}
