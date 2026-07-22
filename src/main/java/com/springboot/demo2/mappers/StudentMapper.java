package com.springboot.demo2.mappers;

import com.springboot.demo2.dtos.StudentRequestDTO;
import com.springboot.demo2.dtos.StudentResponseDTO;
import com.springboot.demo2.entities.LessonEntity;
import com.springboot.demo2.entities.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public StudentResponseDTO toResponseDTO(StudentEntity student){
        if (student == null){
            return null;
        }

        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getStudentId());
        dto.setName(student.getName());
        dto.setSurname(student.getSurname());
        dto.setEmail(student.getEmailAddress());

        if (student.getLessons() != null){
            dto.setLessonNames(student.getLessons().stream()
                    .map(LessonEntity::getLessonName)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
    public StudentEntity toEntity(StudentRequestDTO dto){
        if (dto == null){
            return null;
        }
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmailAddress(dto.getMailAddress());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    public void updateEntityFromDTO(StudentRequestDTO dto , StudentEntity entity){
        if (dto == null){
            return;
        }
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmailAddress(dto.getMailAddress());
        entity.setPassword(dto.getPassword());
    }

}
