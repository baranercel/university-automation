package com.springboot.demo2.mappers;

import com.springboot.demo2.dtos.TeacherRequestDTO;
import com.springboot.demo2.dtos.TeacherResponseDTO;
import com.springboot.demo2.entities.LessonEntity;
import com.springboot.demo2.entities.TeacherEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TeacherMapper {

    public TeacherResponseDTO teacherResponseDTO(TeacherEntity entity){
        TeacherResponseDTO responseDTO = new TeacherResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setEmail(entity.getEmail());
        if (entity.getLessons() != null){
            responseDTO.setLessons(entity.getLessons().stream()
                    .map(LessonEntity::getLessonName)
                    .collect(Collectors.toList()));
        }
        return responseDTO;
    }

    public TeacherEntity toEntity(TeacherRequestDTO dto){
        TeacherEntity entity = new TeacherEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());

        return entity;
    }

    public void updateEntityFromDTO(TeacherRequestDTO dto, TeacherEntity entity) {
        if (dto == null) {
            return;
        }

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());

    }

}
