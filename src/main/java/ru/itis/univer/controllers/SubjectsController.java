package ru.itis.univer.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.univer.dto.SubjectDto;
import ru.itis.univer.security.details.UserDetailsImpl;
import ru.itis.univer.services.SubjectsService;

@Controller
public class SubjectsController {

    private final SubjectsService subjectsService;

    public SubjectsController(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    @GetMapping("/subjects")
    public String getAllSubjects(Model model) {
        model.addAttribute("subjects", SubjectDto.from(subjectsService.getAllSubjects()));
        return "subjects";
    }

    @GetMapping("/subjects/{id}")
    public String getSubject(@PathVariable("id") Long id,
                             Model model) {
        model.addAttribute("subject", subjectsService.getSubject(id));
        return "subject";
    }

    @GetMapping("/subjects/my")
    public String getUsersSubjects(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                   Model model) {
        model.addAttribute("subjects", subjectsService.getAllSubjectByUser(userDetails.getUser()));
        return "subjects";
    }

    @GetMapping("/newSubject")
    public String subjectCreatingPage() {
        return "newSubject";
    }

    @PostMapping("/createNewSubject")
    public String createNewSubject(@RequestParam("name") String name,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        subjectsService.createSubject(name, userDetails.getUser());
        return "redirect:/subjects";
    }

    @DeleteMapping("/deleteSubject")
    public String deleteSubject(@RequestParam("id") Long id) {
        subjectsService.deleteSubject(id);
        return "redirect:/subjects";
    }
}
