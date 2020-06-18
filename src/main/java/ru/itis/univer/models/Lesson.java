package ru.itis.univer.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name = "date_of_event", columnDefinition="TIMESTAMP", nullable = false)
    private LocalDateTime dateOfEvent;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
