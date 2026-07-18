package com.springboot.demo2.services;

import com.springboot.demo2.dtos.TeacherRequestDTO;
import com.springboot.demo2.dtos.TeacherResponseDTO;
import com.springboot.demo2.entities.TeacherEntity;
import com.springboot.demo2.mappers.TeacherMapper;
import com.springboot.demo2.repositories.TeachersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeachersRepository teacherRepository;


    private final TeacherMapper teacherMapper;

    @Override
    public Page<TeacherResponseDTO> getAllTeachers(int  pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<TeacherEntity> teacherPage = teacherRepository.findAll(pageable);
        return teacherPage.map(teacherMapper::teacherResponseDTO);
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
