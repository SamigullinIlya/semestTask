package ru.itis.univer.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.univer.models.Subject;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class SubjectDto {
    private Long id;
    private String name;
    private String teacherName;
    private Long teacherId;

    public static SubjectDto from(Subject subject) {
        return SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .teacherName(subject.getTeacher().getUsername())
                .teacherId(subject.getTeacher().getId())
                .build();
    }

    public static List<SubjectDto> from(List<Subject> subjects) {
        return subjects.stream()
                .map(SubjectDto::from)
                .collect(Collectors.toList());
    }
}
