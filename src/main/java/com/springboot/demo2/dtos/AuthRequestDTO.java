package com.springboot.demo2.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDTO {

    @NotBlank(message = "Email can't be blank!")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password can't be blank!")
    private String password;

}
