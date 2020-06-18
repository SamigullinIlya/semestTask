package ru.itis.univer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.univer.dto.SubjectsSearchResultDto;
import ru.itis.univer.services.SubjectsService;

@RestController
public class SearchSubjectsController {

    private final SubjectsService subjectsService;

    public SearchSubjectsController(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    @GetMapping("/searchSubjects")
    public SubjectsSearchResultDto searchSubject(@RequestParam("query") String query,
                                                 @RequestParam("page") Integer page,
                                                 @RequestParam("size") Integer size) {
        return subjectsService.searchSubject(query, size, page);
    }
}
