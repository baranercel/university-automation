package com.springboot.demo2.controllers;


import com.springboot.demo2.entities.StudentEntity;
import com.springboot.demo2.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents(){
        List<StudentEntity> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);  // HTTP 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Integer id){
        StudentEntity student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);  // HTTP 200 OK
    }

    @PostMapping
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity student){
        StudentEntity createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);  // HTTP 201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Integer id, @RequestBody StudentEntity student){
        StudentEntity updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);  // HTTP 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

}
