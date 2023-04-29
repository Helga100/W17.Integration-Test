package com.example.hw16nosqldb.person_service;

import com.example.hw16nosqldb.person_dto.NewPersonDto;
import com.example.hw16nosqldb.person_dto.PersonInfoDto;

import java.util.List;

public interface PersonService {

    PersonInfoDto create(NewPersonDto newPersonDto);

    List<PersonInfoDto> getByFirstName(String firstName);

    List<PersonInfoDto> getByLastName(String lastName);

    PersonInfoDto getByEmail(String email);

    List<PersonInfoDto> getPersonsOlderThan(int age);

    List<PersonInfoDto> getPersonsByMarriageStatus(boolean isMarried);

    List<PersonInfoDto> getAll();
}
