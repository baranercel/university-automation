package com.springboot.demo2.controllers;


import com.springboot.demo2.dtos.StudentRequestDTO;
import com.springboot.demo2.dtos.StudentResponseDTO;
import com.springboot.demo2.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<StudentResponseDTO>> getAllStudents(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(studentService.getAllStudents(pageNo, pageSize));  // HTTP 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Integer id) {
        StudentResponseDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);  // HTTP 200 OK
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO student) {
        StudentResponseDTO createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);  // HTTP 201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Integer id, @Valid @RequestBody StudentRequestDTO student) {
        StudentResponseDTO updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);  // HTTP 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

}
