package com.springboot.demo2.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class StudentRequestDTO {

    @NotBlank(message = "Student name can't be blank!")
    @Size(min = 2,message = "Student name must be at least 2 characters")
    private String name;

    @NotBlank(message = "Student surname cant' be blank!")
    @Size(min = 2,message = "Student surname must be at least 2 characters")
    private String surname;

    @NotBlank(message = "Email address can't be blank!")
    @Email(message = "Please provide a valid email address!")
    private String emailAddress;

    @NotEmpty(message = "At least one lesson must be selected")
    private List<Integer> lessonIds;

    @NotBlank(message = "Password can't be blank!")
    @Size(min = 6,message = "Password must be at least 6 characters")
    private String password;

}
