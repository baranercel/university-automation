package com.springboot.demo2.controllers;


import com.springboot.demo2.entities.StudentEntity;
import com.springboot.demo2.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentEntity> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentEntity getStudentById(@PathVariable Integer id){
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentEntity createStudent(@RequestBody StudentEntity student){
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public StudentEntity updateStudent(@PathVariable Integer id, @RequestBody StudentEntity student){
        return studentService.updateStudent(id,student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
    }

}
