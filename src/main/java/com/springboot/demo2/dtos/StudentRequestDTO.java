package com.springboot.demo2.dtos;

import lombok.Data;

import java.util.List;

@Data
public class StudentRequestDTO {

    private String name;
    private String surname;
    private String mailAddress;
    private List<Integer> lessonIds;

}
