package com.springboot.demo2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name="students")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    private String name;
    private String surname;
    private String mailAddress;

    @ManyToMany
    @JoinTable(
            name = "students_lessons",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )

    private List<LessonEntity> lessons;


}
