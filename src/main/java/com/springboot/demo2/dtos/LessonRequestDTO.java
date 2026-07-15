package com.springboot.demo2.dtos;

import lombok.Data;

import java.util.List;

@Data
public class LessonRequestDTO {

    private String lessonName;
    private Integer teacherId;

}
