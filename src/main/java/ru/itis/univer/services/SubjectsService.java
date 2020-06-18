package ru.itis.univer.services;

import ru.itis.univer.dto.SubjectsSearchResultDto;
import ru.itis.univer.models.Subject;
import ru.itis.univer.models.User;

import java.util.List;

public interface SubjectsService {
    Subject getSubject(Long id);

    SubjectsSearchResultDto searchSubject(String query, Integer size, Integer page);

    List<Subject> getAllSubjectByUser(User user);
    List<Subject> getAllSubjects();

    void createSubject(String name, User teacher);
    void deleteSubject(Long id);
}
