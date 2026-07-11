package com.springboot.demo2.services;

import com.springboot.demo2.entities.LessonEntity;
import com.springboot.demo2.repositories.LessonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonsRepository lessonRepository;

    @Override
    public List<LessonEntity> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public LessonEntity createLesson(LessonEntity lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public LessonEntity updateLesson(Integer id, LessonEntity newLesson) {
        LessonEntity existingEntity = lessonRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lesson with id " + id + " not found"));
        existingEntity.setLessonName(newLesson.getLessonName());
        existingEntity.setTeacher(newLesson.getTeacher());
        return lessonRepository.save(existingEntity);
    }

    @Override
    public LessonEntity getLessonById(Integer id) {
        return lessonRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lesson with id " + id + " not found")
        );
    }

    @Override
    public void deleteLesson(Integer id) {
        lessonRepository.deleteById(id);
    }

}
