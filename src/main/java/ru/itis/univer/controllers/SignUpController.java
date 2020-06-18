package ru.itis.univer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.univer.forms.SignUpForm;
import ru.itis.univer.services.SignUpService;

@Controller
public class SignUpController {

    private final SignUpService service;

    public SignUpController(SignUpService service) {
        this.service = service;
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpForm form) {
        service.signUp(form);
        return "redirect:/signUp";
    }
}
