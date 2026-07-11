package com.springboot.demo2.controllers;


import com.springboot.demo2.entities.TeacherEntity;
import com.springboot.demo2.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherEntity> getAllTeacher(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public TeacherEntity getTeacherById(@PathVariable Integer id){
        return teacherService.getTeacherById(id);
    }

    @PostMapping
    public TeacherEntity createTeacher(@RequestBody TeacherEntity teacher){
        return teacherService.createTeacher(teacher);
    }

    @PutMapping("/{id}")
    public TeacherEntity updateTeacher(@PathVariable Integer id, @RequestBody TeacherEntity teacher){
        return teacherService.updateTeacher(id, teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Integer id){
        teacherService.deleteTeacher(id);
    }

}
