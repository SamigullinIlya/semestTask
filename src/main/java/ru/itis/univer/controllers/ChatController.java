package ru.itis.univer.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.univer.dto.UserDto;
import ru.itis.univer.models.User;
import ru.itis.univer.security.details.UserDetailsImpl;
import ru.itis.univer.services.MessagesService;
import ru.itis.univer.services.UsersService;

@Controller
public class ChatController {

    private final UsersService usersService;
    private final MessagesService messagesService;

    public ChatController(UsersService usersService, MessagesService messagesService) {
        this.usersService = usersService;
        this.messagesService = messagesService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/{id}/chat")
    public String getChat(@PathVariable("id") Long id,
                          @AuthenticationPrincipal UserDetailsImpl userDetails,
                          Model model) {
        User admin = userDetails.getUser();
        User user = usersService.getOne(id);
        model.addAttribute("user", UserDto.from(user));
        model.addAttribute("admin", UserDto.from(admin));
        model.addAttribute("messages", messagesService.getAllMessages(user));
        return "chat";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/chat")
    public String getChatPage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                              Model model) {
        User user = userDetails.getUser();
        model.addAttribute("messages", messagesService.getAllMessages(user));
        model.addAttribute("user", UserDto.from(user));
        return "chat";
    }
}
