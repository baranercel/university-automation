package com.springboot.demo2.services;

import com.springboot.demo2.dtos.TeacherResponseDTO;
import com.springboot.demo2.entities.TeacherEntity;

import java.util.List;

public interface TeacherService {
    List<TeacherResponseDTO> getAllTeachers();

    TeacherEntity createTeacher(TeacherEntity teacherEntity);

    TeacherEntity updateTeacher(Integer id, TeacherEntity newTeacherEntity);

    TeacherResponseDTO getTeacherById(Integer id);

    void deleteTeacher(Integer id);
}
