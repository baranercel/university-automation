package com.springboot.demo2.services;

import com.springboot.demo2.dtos.LessonDTO;
import com.springboot.demo2.entities.LessonEntity;
import com.springboot.demo2.entities.StudentEntity;
import com.springboot.demo2.repositories.LessonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonsRepository lessonRepository;

    @Override
    public List<LessonDTO> getAllLessons() {
        List<LessonEntity> lessonEntities = lessonRepository.findAll();
        return lessonEntities.stream()
                .map(this::convertToDTO)
                .toList();
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
    public LessonDTO getLessonById(Integer id) {
        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lesson with id " + id + " not found")
        );
        return convertToDTO(lessonEntity);
    }

    @Override
    public void deleteLesson(Integer id) {
        lessonRepository.deleteById(id);
    }

    private LessonDTO convertToDTO(LessonEntity lessonEntity) {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonId(lessonEntity.getLessonId());
        lessonDTO.setLessonName(lessonEntity.getLessonName());
        if (lessonEntity.getTeacher() != null) {
            String fullName = lessonEntity.getTeacher().getName();
            lessonDTO.setTeacherName(fullName);
        }
        return lessonDTO;
    }

}
