package com.springboot.demo2.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class LessonRequestDTO {

    @NotBlank(message = "Lesson name can't be blank!")
    @Size(min = 3,max = 100,message = "Lesson name must be between 3 and 100 characters!")
    private String lessonName;

    @NotNull(message = "Teacher ID is required")
    private Integer teacherId;

}
