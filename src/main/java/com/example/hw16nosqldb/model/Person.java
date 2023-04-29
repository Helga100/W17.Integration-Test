package com.example.hw16nosqldb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("person")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private boolean isMarried;

}
