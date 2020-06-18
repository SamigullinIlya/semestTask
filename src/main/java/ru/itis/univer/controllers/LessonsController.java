package ru.itis.univer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.univer.forms.LessonForm;
import ru.itis.univer.services.LessonsService;
import ru.itis.univer.services.SubjectsService;

@Controller
public class LessonsController {

    private final SubjectsService subjectsService;
    private final LessonsService lessonsService;

    public LessonsController(SubjectsService subjectsService, LessonsService lessonsService) {
        this.subjectsService = subjectsService;
        this.lessonsService = lessonsService;
    }

    @GetMapping("/subjects/{id}/lessons")
    public String getSubject(@PathVariable("id") Long id,
                             Model model) {
        model.addAttribute("lessons", subjectsService.getSubject(id).getLessons());
        return "lessons";
    }

    @PostMapping("/createNewLesson")
    public String createNewLesson(@ModelAttribute("form") LessonForm form) {
        lessonsService.createLesson(form);
        return "redirect:/subjects";
    }

    @DeleteMapping("/deleteLesson")
    public String deleteLesson(@RequestParam("id") Long id) {
        lessonsService.deleteLesson(id);
        return "redirect:/subjects";
    }
}
