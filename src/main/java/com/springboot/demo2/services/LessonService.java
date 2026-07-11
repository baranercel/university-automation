package com.springboot.demo2.services;

import com.springboot.demo2.entities.LessonEntity;

import java.util.List;

public interface LessonService {
    List<LessonEntity> getAllLessons();

    LessonEntity updateLesson(Integer id, LessonEntity newLesson);

    LessonEntity createLesson(LessonEntity lesson);

    LessonEntity getLessonById(Integer id);

    void deleteLesson(Integer id);


}
