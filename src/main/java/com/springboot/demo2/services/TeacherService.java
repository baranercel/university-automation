package com.springboot.demo2.services;

import com.springboot.demo2.dtos.TeacherRequestDTO;
import com.springboot.demo2.dtos.TeacherResponseDTO;
import org.springframework.data.domain.Page;


public interface TeacherService {
    Page<TeacherResponseDTO> getAllTeachers(int pageNo, int pageSize);

    TeacherResponseDTO createTeacher(TeacherRequestDTO teacherRequestDTO);

    TeacherResponseDTO updateTeacher(Integer id, TeacherRequestDTO teacherRequestDTO);

    TeacherResponseDTO getTeacherById(Integer id);

    void deleteTeacher(Integer id);
}
