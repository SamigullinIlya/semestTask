package ru.itis.univer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.univer.models.Lesson;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {
}
