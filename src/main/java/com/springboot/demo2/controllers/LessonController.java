package com.springboot.demo2.controllers;

import com.springboot.demo2.dtos.LessonResponseDTO;
import com.springboot.demo2.entities.LessonEntity;
import com.springboot.demo2.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;


    @GetMapping
    public ResponseEntity<List<LessonResponseDTO>> getAllLessons(){
        List<LessonResponseDTO> lessons = lessonService.getAllLessons();
        return ResponseEntity.ok(lessons);  // HTTP 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> getLessonById(@PathVariable Integer id){
        LessonResponseDTO lesson = lessonService.getLessonById(id);
        return ResponseEntity.ok(lesson);   // HTTP 200 OK
    }

    @PostMapping
    public ResponseEntity<LessonEntity> createLesson(@RequestBody LessonEntity lesson){
        LessonEntity createdLesson = lessonService.createLesson(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);  // HTTP 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonEntity> updateLesson(@PathVariable Integer id, @RequestBody LessonEntity lesson){
        LessonEntity updatedLesson = lessonService.updateLesson(id, lesson);
        return ResponseEntity.ok(updatedLesson);   // HTTP 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Integer id){
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build(); //HTTP 204 No Content
    }

}
