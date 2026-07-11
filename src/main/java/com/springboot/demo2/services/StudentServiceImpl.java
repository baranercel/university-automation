package com.springboot.demo2.services;

import com.springboot.demo2.entities.StudentEntity;
import com.springboot.demo2.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity createStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    @Override
    public StudentEntity updateStudent(Integer id, StudentEntity newStudent) {
        StudentEntity existingStudent = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found"));
        existingStudent.setName(newStudent.getName());
        existingStudent.setSurname(newStudent.getSurname());
        existingStudent.setMailAddress(newStudent.getMailAddress());
        return studentRepository.save(existingStudent);
    }

    @Override
    public StudentEntity getStudentById(Integer id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found"));
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
