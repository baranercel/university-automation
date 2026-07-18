package com.springboot.demo2.services;

import com.springboot.demo2.dtos.TeacherRequestDTO;
import com.springboot.demo2.dtos.TeacherResponseDTO;
import com.springboot.demo2.entities.TeacherEntity;
import com.springboot.demo2.mappers.TeacherMapper;
import com.springboot.demo2.repositories.TeachersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeachersRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    @Override
    public Page<TeacherResponseDTO> getAllTeachers(int  pageNo, int pageSize) {
        log.info("getAllTeachers() method invoked. Requested Page No: {}, Page Size: {}", pageNo, pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<TeacherEntity> teacherPage = teacherRepository.findAll(pageable);
        log.info("Successfully fetched {} teachers from the database.",teacherPage.getNumberOfElements());
        return teacherPage.map(teacherMapper::teacherResponseDTO);
    }

    @Override
    public TeacherResponseDTO createTeacher(TeacherRequestDTO teacher) {
        log.info("createTeacher() method invoked. Requested Teacher: {}", teacher);
        TeacherEntity newTeacher = teacherMapper.toEntity(teacher);

        TeacherEntity createdTeacher = teacherRepository.save(newTeacher);
        log.info("Successfully created the teacher from the database. Teacher Id {}", createdTeacher.getId());

        return teacherMapper.teacherResponseDTO(createdTeacher);
    }

    @Override
    public TeacherResponseDTO updateTeacher(Integer id, TeacherRequestDTO newTeacher) {
        log.info("updateTeacher() method invoked. Requested Teacher: {}", newTeacher);
        TeacherEntity existingTeacher = teacherRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Teacher with id " + id + " not found"));
        teacherMapper.updateEntityFromDTO(newTeacher, existingTeacher);

        TeacherEntity updatedEntity = teacherRepository.save(existingTeacher);
        log.info("Successfully updated the teacher from the database. Teacher Id {}", updatedEntity.getId());

        return teacherMapper.teacherResponseDTO(updatedEntity);
    }

    @Override
    public TeacherResponseDTO getTeacherById(Integer id) {
        log.info("getTeacherById() method invoked. Requested Teacher: {}", id);
        TeacherEntity teacherEntity = teacherRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Teacher not found")
        );
        log.info("Successfully found the teacher from the database. Teacher Id {}", teacherEntity.getId());
        return teacherMapper.teacherResponseDTO(teacherEntity);
    }

    @Override
    public void deleteTeacher(Integer id) {
        log.info("deleteTeacher() method invoked. Requested Teacher: {}", id);
        teacherRepository.deleteById(id);
    }

}
