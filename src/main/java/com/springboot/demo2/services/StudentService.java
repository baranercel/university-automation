package com.springboot.demo2.services;

import com.springboot.demo2.dtos.StudentRequestDTO;
import com.springboot.demo2.dtos.StudentResponseDTO;
import com.springboot.demo2.entities.StudentEntity;

import java.util.List;

public interface StudentService {

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO createStudent(StudentRequestDTO student);

    StudentResponseDTO updateStudent(Integer id, StudentRequestDTO newStudent);

    StudentResponseDTO getStudentById(Integer id);

    void deleteStudent(Integer id);

}
