package com.springboot.demo2.services;

import com.springboot.demo2.dtos.StudentDTO;
import com.springboot.demo2.entities.StudentEntity;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentEntity createStudent(StudentEntity student);

    StudentEntity updateStudent(Integer id, StudentEntity newStudent);

    StudentDTO getStudentById(Integer id);

    void deleteStudent(Integer id);

}
