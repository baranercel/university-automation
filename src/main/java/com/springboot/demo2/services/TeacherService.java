package com.springboot.demo2.services;

import com.springboot.demo2.dtos.TeacherDTO;
import com.springboot.demo2.entities.TeacherEntity;

import java.util.List;

public interface TeacherService {
    List<TeacherDTO> getAllTeachers();

    TeacherEntity createTeacher(TeacherEntity teacherEntity);

    TeacherEntity updateTeacher(Integer id, TeacherEntity newTeacherEntity);

    TeacherDTO getTeacherById(Integer id);

    void deleteTeacher(Integer id);
}
