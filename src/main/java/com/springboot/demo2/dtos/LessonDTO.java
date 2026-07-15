package com.springboot.demo2.dtos;

import lombok.Data;

import java.util.List;

@Data
public class LessonDTO {

    private Integer lessonId;
    private String lessonName;
    private String teacherName;

}
