package ru.itis.univer.services.impls;

import org.springframework.stereotype.Service;
import ru.itis.univer.forms.LessonForm;
import ru.itis.univer.models.Lesson;
import ru.itis.univer.repositories.LessonsRepository;
import ru.itis.univer.repositories.SubjectsRepository;
import ru.itis.univer.services.LessonsService;

@Service
public class LessonsServiceImpl implements LessonsService {

    private final LessonsRepository lessonsRepository;
    private final SubjectsRepository subjectsRepository;

    public LessonsServiceImpl(LessonsRepository lessonsRepository, SubjectsRepository subjectsRepository) {
        this.lessonsRepository = lessonsRepository;
        this.subjectsRepository = subjectsRepository;
    }

    @Override
    public void createLesson(LessonForm form) {
        lessonsRepository.save(Lesson.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .dateOfEvent(form.getDate())
                .subject(subjectsRepository.getOne(form.getSubjectId()))
                .build());
    }

    @Override
    public void deleteLesson(Long id) {
        Lesson lesson = lessonsRepository.getOne(id);
        lessonsRepository.delete(lesson);
    }
}
