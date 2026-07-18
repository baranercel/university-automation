package com.springboot.demo2.services;

import com.springboot.demo2.dtos.LessonRequestDTO;
import com.springboot.demo2.dtos.LessonResponseDTO;
import com.springboot.demo2.entities.LessonEntity;
import com.springboot.demo2.mappers.LessonMapper;
import com.springboot.demo2.repositories.TeachersRepository;
import com.springboot.demo2.entities.TeacherEntity;
import com.springboot.demo2.repositories.LessonsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonsRepository lessonRepository;

    private final TeachersRepository teachersRepository;

    private final LessonMapper lessonMapper;

    @Override
    public Page<LessonResponseDTO> getAllLessons(int pageNo, int pageSize) {
        log.info("getAllLessons() method invoked. Requested Page: {} Requested Size: {}", pageNo ,  pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<LessonEntity> lessonPage = lessonRepository.findAll(pageable);
        log.info("Successfully got the lessons from the database.");
        return lessonPage.map(lessonMapper::toResponseDTO);
    }

    @Override
    public LessonResponseDTO createLesson(LessonRequestDTO lesson) {
        log.info("createLesson() method invoked. Requested Lesson: {}", lesson);
        LessonEntity newLesson = lessonMapper.toEntity(lesson);

        if (lesson.getTeacherId() != null) {
            TeacherEntity teacher = teachersRepository.findById(lesson.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + lesson.getTeacherId()));
            log.info("The teacher was successfully assigned to the class");
            newLesson.setTeacher(teacher);
        }

        LessonEntity savedLesson = lessonRepository.save(newLesson);
        log.info("Successfully created the lesson from the database.");
        return lessonMapper.toResponseDTO(savedLesson);
    }

    @Override
    public LessonResponseDTO updateLesson(Integer id, LessonRequestDTO newLesson) {
        log.info("updateLesson() method invoked. Requested Lesson: {}", newLesson);
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
        log.info("Successfully updated the lesson from the database.");

        return lessonMapper.toResponseDTO(updatedEntity);
    }

    @Override
    public LessonResponseDTO getLessonById(Integer id) {
        log.info("getLessonById() method invoked. Requested Lesson: {}", id);
        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lesson with id " + id + " not found")
        );
        log.info("Successfully get the lesson from the database.");
        return lessonMapper.toResponseDTO(lessonEntity);
    }

    @Override
    public void deleteLesson(Integer id) {
        log.info("deleteLesson() method invoked. Requested Lesson: {}", id);
        lessonRepository.deleteById(id);
    }

}
