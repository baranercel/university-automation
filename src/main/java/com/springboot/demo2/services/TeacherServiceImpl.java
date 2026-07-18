package com.springboot.demo2.services;

import com.springboot.demo2.dtos.TeacherRequestDTO;
import com.springboot.demo2.dtos.TeacherResponseDTO;
import com.springboot.demo2.entities.TeacherEntity;
import com.springboot.demo2.mappers.TeacherMapper;
import com.springboot.demo2.repositories.LessonsRepository;
import com.springboot.demo2.repositories.TeachersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeachersRepository teacherRepository;

    private final LessonsRepository lessonsRepository;

    private final TeacherMapper teacherMapper;

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {
        List<TeacherEntity> teacherEntities = teacherRepository.findAll();
        return teacherEntities.stream()
                .map(teacherMapper::teacherResponseDTO)
                .toList();
    }

    @Override
    public TeacherResponseDTO createTeacher(TeacherRequestDTO teacher) {

        TeacherEntity newTeacher = teacherMapper.toEntity(teacher);

        TeacherEntity createdTeacher = teacherRepository.save(newTeacher);

        return teacherMapper.teacherResponseDTO(createdTeacher);
    }

    @Override
    public TeacherResponseDTO updateTeacher(Integer id, TeacherRequestDTO newTeacher) {
        TeacherEntity existingTeacher = teacherRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Teacher with id " + id + " not found"));
        teacherMapper.updateEntityFromDTO(newTeacher, existingTeacher);

        TeacherEntity updatedEntity = teacherRepository.save(existingTeacher);

        return teacherMapper.teacherResponseDTO(updatedEntity);
    }

    @Override
    public TeacherResponseDTO getTeacherById(Integer id) {
        TeacherEntity teacherEntity = teacherRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Teacher not found")
        );
        return teacherMapper.teacherResponseDTO(teacherEntity);
    }

    @Override
    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }

}
