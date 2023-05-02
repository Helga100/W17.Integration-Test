package com.example.hw16nosqldb.person_dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class NewPersonDto {

    @Size(min = 2)
    private String firstName;
    @Size(min = 3)
    private String lastName;
    private int age;
    @Email
    private String email;
    private boolean married;

}
