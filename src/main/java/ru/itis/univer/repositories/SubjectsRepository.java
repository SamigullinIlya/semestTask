package ru.itis.univer.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.univer.models.Subject;
import ru.itis.univer.models.User;

import java.util.List;

public interface SubjectsRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByStudentsIn(List<User> students);
    //Илюх начинай писать фронт. У тебя мало времени. Более того, ты не успеешь за 3 часа
    //Родь, скинь илюхе несколько шаблонов

    @Query("from Subject subject where upper(subject.name) like concat('%', upper(:query), '%')")
    Page<Subject> search(@Param("query") String query,
                         Pageable pageable);
}
