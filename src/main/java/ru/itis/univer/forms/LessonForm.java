package ru.itis.univer.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonForm {
     private String title;
     private String content;
     private LocalDateTime date;
     private Long subjectId;
}
