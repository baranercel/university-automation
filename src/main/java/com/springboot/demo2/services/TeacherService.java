package com.springboot.demo2.services;

import com.springboot.demo2.dtos.TeacherRequestDTO;
import com.springboot.demo2.dtos.TeacherResponseDTO;
import com.springboot.demo2.entities.TeacherEntity;

import java.util.List;

public interface TeacherService {
    List<TeacherResponseDTO> getAllTeachers();

    TeacherResponseDTO createTeacher(TeacherRequestDTO teacherRequestDTO);

    TeacherResponseDTO updateTeacher(Integer id, TeacherRequestDTO teacherRequestDTO);

    TeacherResponseDTO getTeacherById(Integer id);

    void deleteTeacher(Integer id);
}
