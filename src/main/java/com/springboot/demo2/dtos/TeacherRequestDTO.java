package com.springboot.demo2.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TeacherRequestDTO {

    @NotBlank(message = "Teacher name can't be blank!")
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String name;

    @NotBlank(message = "Teacher surname can't be blank!")
    @Size(min = 6,message = "Surname must be at least 2 characters")
    private String surname;

    @NotBlank(message = "Email address can't be blank!")
    @Email(message = "Please provide a valid email address!")
    private String email;

    @NotBlank(message = "Password can't be blank!")
    @Size(min = 6,message = "Password must be at least 6 characters")
    private String password;

}
