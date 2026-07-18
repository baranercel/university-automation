package com.springboot.demo2.services;

import com.springboot.demo2.dtos.LessonRequestDTO;
import com.springboot.demo2.dtos.LessonResponseDTO;
import org.springframework.data.domain.Page;

public interface LessonService {
    Page<LessonResponseDTO> getAllLessons(int pageNo, int pageSize);

    LessonResponseDTO updateLesson(Integer id, LessonRequestDTO newLesson);

    LessonResponseDTO createLesson(LessonRequestDTO lesson);

    LessonResponseDTO getLessonById(Integer id);

    void deleteLesson(Integer id);


}
