package ru.itis.univer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.univer.dto.UserDto;
import ru.itis.univer.forms.UserForm;
import ru.itis.univer.models.User;
import ru.itis.univer.security.details.UserDetailsImpl;
import ru.itis.univer.services.UsersService;

import javax.validation.Valid;

@Slf4j
@Controller
public class ProfileController {

    private final UsersService usersService;

    public ProfileController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails,
                             Model model) {
        model.addAttribute("user", UserDto.from(userDetails.getUser()));
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                @Valid UserForm userForm,
                                BindingResult bindingResult,
                                Model model) {
        User user = userDetails.getUser();
        if (!bindingResult.hasErrors()) {
            user.setUsername(userForm.getUsername());
            user.setEmail(userForm.getEmail());
            usersService.updateUser(user);
        } else {
            model.addAttribute("errors", bindingResult.getAllErrors());
            log.error(bindingResult.getAllErrors().toString());
        }
        model.addAttribute(UserDto.from(user));
        return "profile";
    }
}
