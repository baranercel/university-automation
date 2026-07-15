package com.springboot.demo2.services;

import com.springboot.demo2.dtos.TeacherResponseDTO;
import com.springboot.demo2.entities.TeacherEntity;
import com.springboot.demo2.repositories.TeachersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeachersRepository teacherRepository;

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {
        List<TeacherEntity> teacherEntities = teacherRepository.findAll();
        return teacherEntities.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public TeacherEntity createTeacher(TeacherEntity teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public TeacherEntity updateTeacher(Integer id, TeacherEntity newTeacher) {
        TeacherEntity existingTeacher = teacherRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Teacher with id " + id + " not found"));
        existingTeacher.setName(newTeacher.getName());
        existingTeacher.setSurname(newTeacher.getSurname());
        existingTeacher.setEmail(newTeacher.getEmail());
        return teacherRepository.save(existingTeacher);
    }

    @Override
    public TeacherResponseDTO getTeacherById(Integer id) {
        TeacherEntity teacherEntity = teacherRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Teacher not found")
        );
        return convertToDTO(teacherEntity);
    }

    @Override
    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }

    private TeacherResponseDTO convertToDTO(TeacherEntity teacherEntity){
        TeacherResponseDTO teacherResponseDTO = new TeacherResponseDTO();
        teacherResponseDTO.setId(teacherEntity.getId());
        teacherResponseDTO.setName(teacherEntity.getName());
        teacherResponseDTO.setSurname(teacherEntity.getSurname());
        teacherResponseDTO.setEmail(teacherEntity.getEmail());
        if (teacherEntity.getLessons() != null) {
            List<String> lessons = teacherEntity.getLessons().stream()
                    .map(lesson -> lesson.getLessonName())
                    .toList();
            teacherResponseDTO.setLessons(lessons);
        }
        return teacherResponseDTO;
    }
}
