package com.springboot.demo2.services;

import com.springboot.demo2.dtos.LessonRequestDTO;
import com.springboot.demo2.dtos.LessonResponseDTO;
import com.springboot.demo2.entities.LessonEntity;
import com.springboot.demo2.mappers.LessonMapper;
import com.springboot.demo2.repositories.TeachersRepository;
import com.springboot.demo2.entities.TeacherEntity;
import com.springboot.demo2.repositories.LessonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonsRepository lessonRepository;

    private final TeachersRepository teachersRepository;

    private final LessonMapper lessonMapper;

    @Override
    public List<LessonResponseDTO> getAllLessons() {
        List<LessonEntity> lessonEntities = lessonRepository.findAll();
        return lessonEntities.stream()
                .map(lessonMapper::toResponseDTO)
                .toList();
    }

    @Override
    public LessonResponseDTO createLesson(LessonRequestDTO lesson) {
        LessonEntity newLesson  = lessonMapper.toEntity(lesson);

        if (lesson.getTeacherId() != null) {
            TeacherEntity teacher = teachersRepository.findById(lesson.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + lesson.getTeacherId()));

            newLesson.setTeacher(teacher);
        }

        LessonEntity savedLesson = lessonRepository.save(newLesson);
        return lessonMapper.toResponseDTO(savedLesson);
    }

    @Override
    public LessonResponseDTO updateLesson(Integer id, LessonRequestDTO newLesson) {
        LessonEntity existingEntity = lessonRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lesson with id " + id + " not found"));
        lessonMapper.updateEntityFromDTO(newLesson, existingEntity);
        if (newLesson.getTeacherId() != null) {
            TeacherEntity teacher = teachersRepository.findById(newLesson.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            existingEntity.setTeacher(teacher);
        } else {
            existingEntity.setTeacher(null);
        }

        LessonEntity updatedEntity = lessonRepository.save(existingEntity);

        return lessonMapper.toResponseDTO(updatedEntity);
    }

    @Override
    public LessonResponseDTO getLessonById(Integer id) {
        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lesson with id " + id + " not found")
        );
        return lessonMapper.toResponseDTO(lessonEntity);
    }

    @Override
    public void deleteLesson(Integer id) {
        lessonRepository.deleteById(id);
    }

}
