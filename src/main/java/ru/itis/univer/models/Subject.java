package ru.itis.univer.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name")
    private String name;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private Set<Lesson> lessons;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToMany(mappedBy = "subjects")
    private Set<User> students;
}
