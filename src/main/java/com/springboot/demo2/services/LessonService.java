package com.springboot.demo2.services;

import com.springboot.demo2.dtos.LessonResponseDTO;
import com.springboot.demo2.entities.LessonEntity;

import java.util.List;

public interface LessonService {
    List<LessonResponseDTO> getAllLessons();

    LessonEntity updateLesson(Integer id, LessonEntity newLesson);

    LessonEntity createLesson(LessonEntity lesson);

    LessonResponseDTO getLessonById(Integer id);

    void deleteLesson(Integer id);


}
