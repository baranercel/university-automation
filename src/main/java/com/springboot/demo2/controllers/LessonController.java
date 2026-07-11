package com.springboot.demo2.controllers;

import com.springboot.demo2.entities.LessonEntity;
import com.springboot.demo2.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;


    @GetMapping
    public List<LessonEntity> getAllLessons(){
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public LessonEntity getLessonById(@PathVariable Integer id){
        return lessonService.getLessonById(id);
    }

    @PostMapping
    public LessonEntity createLesson(@RequestBody LessonEntity lesson){
        return lessonService.createLesson(lesson);
    }

    @PutMapping("/{id}")
    public LessonEntity updateLesson(@PathVariable Integer id, @RequestBody LessonEntity lesson){
        return lessonService.updateLesson(id, lesson);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Integer id){
        lessonService.deleteLesson(id);
    }

}
