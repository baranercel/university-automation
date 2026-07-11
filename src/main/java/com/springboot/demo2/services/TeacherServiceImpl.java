package com.springboot.demo2.services;

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
    public List<TeacherEntity> getAllTeachers() {
        return teacherRepository.findAll();
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
    public TeacherEntity getTeacherById(Integer id) {
        return teacherRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Teacher not found")
        );
    }

    @Override
    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }
}
