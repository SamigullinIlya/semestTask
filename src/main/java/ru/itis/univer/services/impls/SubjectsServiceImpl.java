package ru.itis.univer.services.impls;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.univer.dto.SubjectsSearchResultDto;
import ru.itis.univer.models.Subject;
import ru.itis.univer.models.User;
import ru.itis.univer.repositories.SubjectsRepository;
import ru.itis.univer.services.SubjectsService;

import java.util.Collections;
import java.util.List;

@Service
public class SubjectsServiceImpl implements SubjectsService {

    private final SubjectsRepository subjectsRepository;

    public SubjectsServiceImpl(SubjectsRepository subjectsRepository) {
        this.subjectsRepository = subjectsRepository;
    }

    @Override
    public Subject getSubject(Long id) {
        return subjectsRepository.getOne(id);
    }

    @Override
    public SubjectsSearchResultDto searchSubject(String query, Integer size, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Subject> pageResult = subjectsRepository.search(query, pageRequest);
        return SubjectsSearchResultDto.builder()
                .subjects(pageResult.getContent())
                .pagesCount(pageResult.getTotalPages())
                .build();
    }

    @Override
    public List<Subject> getAllSubjectByUser(User user) {
        return subjectsRepository.findAllByStudentsIn(Collections.singletonList(user));
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectsRepository.findAll();
    }

    @Override
    public void createSubject(String name, User teacher) {
        subjectsRepository.save(Subject.builder().name(name).teacher(teacher).build());
    }

    @Override
    public void deleteSubject(Long id) {
        Subject subject = subjectsRepository.getOne(id);
        subjectsRepository.delete(subject);
    }
}
