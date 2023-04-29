package com.example.hw16nosqldb.person_repository;

import com.example.hw16nosqldb.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    List<Person> findByFirstName(String firstName);

    List<Person> findByLastName(String lastName);

    Person findByEmail(String email);

    List<Person> findByAgeGreaterThan(int age);

    List<Person> findByMarried(boolean isMarried);
}
