package com.springboot.demo2.services;

import com.springboot.demo2.dtos.StudentRequestDTO;
import com.springboot.demo2.dtos.StudentResponseDTO;
import com.springboot.demo2.entities.LessonEntity;
import com.springboot.demo2.entities.StudentEntity;
import com.springboot.demo2.repositories.LessonsRepository;
import com.springboot.demo2.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final LessonsRepository lessonsRepository;

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(student.getName());
        studentEntity.setSurname(student.getSurname());
        studentEntity.setMailAddress(student.getMailAddress());
        if (student.getLessonIds() != null && !student.getLessonIds().isEmpty()) {
            List<LessonEntity> selectedLessons = lessonsRepository.findAllById(student.getLessonIds());

            studentEntity.setLessons(selectedLessons);
        }

        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);

        return convertToDTO(savedStudentEntity);
    }

    @Override
    public StudentResponseDTO updateStudent(Integer id, StudentRequestDTO newStudent) {
        StudentEntity existingStudent = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found"));
        existingStudent.setName(newStudent.getName());
        existingStudent.setSurname(newStudent.getSurname());
        existingStudent.setMailAddress(newStudent.getMailAddress());

        if (newStudent.getLessonIds() != null && !newStudent.getLessonIds().isEmpty()) {
            List<LessonEntity> updatedLessons = lessonsRepository.findAllById(newStudent.getLessonIds());
            existingStudent.setLessons(updatedLessons);
        } else {
            existingStudent.setLessons(null);
        }

        StudentEntity savedStudentEntity = studentRepository.save(existingStudent);

        return convertToDTO(savedStudentEntity);
    }

    @Override
    public StudentResponseDTO getStudentById(Integer id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found"));
        return convertToDTO(studentEntity);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    private StudentResponseDTO convertToDTO(StudentEntity entity) {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(entity.getStudentId());
        studentResponseDTO.setName(entity.getName());
        studentResponseDTO.setSurname(entity.getSurname());
        studentResponseDTO.setEmail(entity.getMailAddress());

        if (entity.getLessons() != null) {
            List<String> lessonNames = entity.getLessons().stream()
                    .map(lesson -> lesson.getLessonName())
                    .toList();
            studentResponseDTO.setLessonNames(lessonNames);
        }
        return studentResponseDTO;
    }
}

