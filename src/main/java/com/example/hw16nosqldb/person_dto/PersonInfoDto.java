package com.example.hw16nosqldb.person_dto;

import lombok.Data;

@Data
public class PersonInfoDto {

    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private boolean isMarried;
}
