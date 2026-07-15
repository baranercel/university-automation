package com.springboot.demo2.services;

import com.springboot.demo2.dtos.LessonDTO;
import com.springboot.demo2.entities.LessonEntity;

import java.util.List;

public interface LessonService {
    List<LessonDTO> getAllLessons();

    LessonEntity updateLesson(Integer id, LessonEntity newLesson);

    LessonEntity createLesson(LessonEntity lesson);

    LessonDTO getLessonById(Integer id);

    void deleteLesson(Integer id);


}
