package com.springboot.demo2.services;

import com.springboot.demo2.entities.StudentEntity;

import java.util.List;

public interface StudentService {

    List<StudentEntity> getAllStudents();

    StudentEntity createStudent(StudentEntity student);

    StudentEntity updateStudent(Integer id, StudentEntity newStudent);

    StudentEntity getStudentById(Integer id);

    void deleteStudent(Integer id);

}
