package ru.itis.univer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.univer.models.Subject;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectsSearchResultDto {
    private List<Subject> subjects;
    private int pagesCount;
}
