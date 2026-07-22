package com.springboot.demo2.dtos;


import lombok.Data;

import java.util.List;

@Data
public class StudentResponseDTO {
    private Integer id;
    private String name;
    private String surname;
    private String emailAddress;
    private List<String> lessonNames;
}
