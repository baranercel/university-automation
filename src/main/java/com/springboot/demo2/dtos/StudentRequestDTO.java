package com.springboot.demo2.dtos;

import lombok.Data;

@Data
public class StudentRequestDTO {

    private String name;
    private String surname;
    private String mailAddress;

}
