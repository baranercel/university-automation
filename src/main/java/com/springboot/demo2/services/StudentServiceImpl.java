package com.springboot.demo2.services;

import com.springboot.demo2.mappers.StudentMapper;
import com.springboot.demo2.dtos.StudentRequestDTO;
import com.springboot.demo2.dtos.StudentResponseDTO;
import com.springboot.demo2.entities.LessonEntity;
import com.springboot.demo2.entities.StudentEntity;
import com.springboot.demo2.repositories.LessonsRepository;
import com.springboot.demo2.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final LessonsRepository lessonsRepository;

    private final StudentMapper studentMapper;

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities.stream()
                .map(studentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO student) {
        StudentEntity studentEntity = studentMapper.toEntity(student);
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);
        return studentMapper.toResponseDTO(savedStudentEntity);
    }

    @Override
    public StudentResponseDTO updateStudent(Integer id, StudentRequestDTO newStudent) {
        StudentEntity existingStudent = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found"));
        studentMapper.updateEntityFromDTO(newStudent, existingStudent);

        if (newStudent.getLessonIds() != null && !newStudent.getLessonIds().isEmpty()) {
            List<LessonEntity> updatedLessons = lessonsRepository.findAllById(newStudent.getLessonIds());
            existingStudent.setLessons(updatedLessons);
        } else {
            existingStudent.setLessons(null);
        }

        StudentEntity savedStudentEntity = studentRepository.save(existingStudent);

        return studentMapper.toResponseDTO(savedStudentEntity);
    }

    @Override
    public StudentResponseDTO getStudentById(Integer id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found"));
        return studentMapper.toResponseDTO(studentEntity);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

}

