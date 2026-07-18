package com.springboot.demo2.services;

import com.springboot.demo2.mappers.StudentMapper;
import com.springboot.demo2.dtos.StudentRequestDTO;
import com.springboot.demo2.dtos.StudentResponseDTO;
import com.springboot.demo2.entities.LessonEntity;
import com.springboot.demo2.entities.StudentEntity;
import com.springboot.demo2.repositories.LessonsRepository;
import com.springboot.demo2.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final LessonsRepository lessonsRepository;

    private final StudentMapper studentMapper;

    @Override
    public Page<StudentResponseDTO> getAllStudents(int pageNo, int pageSize) {
        log.info("getAllStudents method invoked. Requested page {}, requested page size {}", pageNo, pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<StudentEntity> studentPage = studentRepository.findAll(pageable);
        log.info("{} students were successfully retrieved from the database." , studentPage.getNumberOfElements());
        return studentPage.map(studentMapper::toResponseDTO);
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO student) {
        log.info("A new students is being added to the system {} {}",student.getName(),student.getSurname());
        StudentEntity studentEntity = studentMapper.toEntity(student);
        if (student.getLessonIds() !=null && !student.getLessonIds().isEmpty()){
            List<LessonEntity> lessons = lessonsRepository.findAllById(student.getLessonIds());
            studentEntity.setLessons(lessons);
            log.debug("The student was assigned {} courses.",lessons.size());
        }
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);
        log.info("Successfully saved the students to the database. Student Id {}",savedStudentEntity.getStudentId());
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
        log.info("Successfully updated the students to the database. Student Id {}",savedStudentEntity.getStudentId());

        return studentMapper.toResponseDTO(savedStudentEntity);
    }

    @Override
    public StudentResponseDTO getStudentById(Integer id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found"));
        log.info("Successfully found the students from the database. Student Id {}",studentEntity.getStudentId());
        return studentMapper.toResponseDTO(studentEntity);
    }

    @Override
    public void deleteStudent(Integer id) {
        log.info("Deleting the student from the database. Student Id {}",id);
        studentRepository.deleteById(id);
    }

}

