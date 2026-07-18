package com.springboot.demo2.controllers;

import com.springboot.demo2.dtos.LessonRequestDTO;
import com.springboot.demo2.dtos.LessonResponseDTO;
import com.springboot.demo2.services.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;


    @GetMapping
    public ResponseEntity<Page<LessonResponseDTO>> getAllLessons(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {

        return ResponseEntity.ok(lessonService.getAllLessons(pageNo, pageSize));  // HTTP 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> getLessonById(@PathVariable Integer id) {
        LessonResponseDTO lesson = lessonService.getLessonById(id);
        return ResponseEntity.ok(lesson);   // HTTP 200 OK
    }

    @PostMapping
    public ResponseEntity<LessonResponseDTO> createLesson(@Valid @RequestBody LessonRequestDTO lesson) {
        LessonResponseDTO createdLesson = lessonService.createLesson(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);  // HTTP 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> updateLesson(@PathVariable Integer id, @Valid @RequestBody LessonRequestDTO lesson) {
        LessonResponseDTO updatedLesson = lessonService.updateLesson(id, lesson);
        return ResponseEntity.ok(updatedLesson);   // HTTP 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Integer id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build(); //HTTP 204 No Content
    }

}
