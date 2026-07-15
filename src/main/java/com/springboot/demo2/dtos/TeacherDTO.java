package com.springboot.demo2.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TeacherDTO {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private List<String> lessons;

}
