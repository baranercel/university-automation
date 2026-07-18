package com.springboot.demo2.mappers;

import com.springboot.demo2.dtos.LessonRequestDTO;
import com.springboot.demo2.dtos.LessonResponseDTO;
import com.springboot.demo2.entities.LessonEntity;
import org.springframework.stereotype.Component;


@Component
public class LessonMapper {

    public LessonResponseDTO toResponseDTO(LessonEntity lesson){
        if (lesson == null){
            return null;
        }
        LessonResponseDTO responseDTO = new LessonResponseDTO();
        responseDTO.setLessonId(lesson.getLessonId());
        responseDTO.setLessonName(lesson.getLessonName());
        if (lesson.getTeacher() != null){
            responseDTO.setTeacherName(lesson.getTeacher().getName());
        }
        return responseDTO;
    }

    public LessonEntity toEntity(LessonRequestDTO dto){
        if (dto == null){
            return null;
        }
        LessonEntity entity = new LessonEntity();
        entity.setLessonName(dto.getLessonName());

        return  entity;
    }

    public void updateEntityFromDTO(LessonRequestDTO dto , LessonEntity entity){
        if (dto == null){
            return;
        }
        entity.setLessonName(dto.getLessonName());

    }
}
