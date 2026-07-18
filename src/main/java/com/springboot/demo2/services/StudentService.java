package com.springboot.demo2.services;

import com.springboot.demo2.dtos.StudentRequestDTO;
import com.springboot.demo2.dtos.StudentResponseDTO;
import org.springframework.data.domain.Page;


public interface StudentService {

    Page<StudentResponseDTO> getAllStudents(int pageNo, int pageSize);

    StudentResponseDTO createStudent(StudentRequestDTO student);

    StudentResponseDTO updateStudent(Integer id, StudentRequestDTO newStudent);

    StudentResponseDTO getStudentById(Integer id);

    void deleteStudent(Integer id);

}
