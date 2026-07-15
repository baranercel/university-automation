package com.springboot.demo2.dtos;

import lombok.Data;

@Data
public class LessonResponseDTO {

    private Integer lessonId;
    private String lessonName;
    private String teacherName;

}
