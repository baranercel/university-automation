package com.springboot.demo2.services;

import com.springboot.demo2.dtos.StudentDTO;
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
    public List<StudentDTO> getAllStudents() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities.stream()
                .map(this::convertToDTO)
                .toList();
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
    public StudentDTO getStudentById(Integer id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found"));
        return convertToDTO(studentEntity);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO convertToDTO(StudentEntity entity) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(entity.getStudentId());
        studentDTO.setName(entity.getName());
        studentDTO.setSurname(entity.getSurname());
        studentDTO.setEmail(entity.getMailAddress());

        if (entity.getLessons() != null) {
            List<String> lessonNames = entity.getLessons().stream()
                    .map(lesson -> lesson.getLessonName())
                    .toList();
        }
        return studentDTO;
    }
}

