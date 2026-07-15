package com.springboot.demo2.services;

import com.springboot.demo2.dtos.LessonRequestDTO;
import com.springboot.demo2.dtos.LessonResponseDTO;
import com.springboot.demo2.entities.LessonEntity;

import java.util.List;

public interface LessonService {
    List<LessonResponseDTO> getAllLessons();

    LessonResponseDTO updateLesson(Integer id, LessonRequestDTO newLesson);

    LessonResponseDTO createLesson(LessonRequestDTO lesson);

    LessonResponseDTO getLessonById(Integer id);

    void deleteLesson(Integer id);


}
