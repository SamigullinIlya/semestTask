package ru.itis.univer.services;

import ru.itis.univer.forms.LessonForm;

public interface LessonsService {
    void createLesson(LessonForm form);
    void deleteLesson(Long id);
}
