package com.example.hw16nosqldb.person_service;

import com.example.hw16nosqldb.exception.PersonNotFoundException;
import com.example.hw16nosqldb.model.Person;
import com.example.hw16nosqldb.person_dto.NewPersonDto;
import com.example.hw16nosqldb.person_dto.PersonInfoDto;
import com.example.hw16nosqldb.person_repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {


    private final ModelMapper modelMapper;
    private final PersonRepository personRepository;

    public PersonServiceImpl(ModelMapper modelMapper, PersonRepository personRepository) {
        this.modelMapper = modelMapper;
        this.personRepository = personRepository;
    }


    @Override
    public PersonInfoDto create(NewPersonDto newPersonDto) {
        Person person = modelMapper.map(newPersonDto, Person.class);
        return modelMapper.map(personRepository.save(person), PersonInfoDto.class);
    }

    @Override
    public List<PersonInfoDto> getByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName)
                .stream()
                .map(person -> modelMapper.map(person, PersonInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonInfoDto> getByLastName(String lastName) {
        return personRepository.findByLastName(lastName).stream()
                .map(person -> modelMapper.map(person, PersonInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonInfoDto getByEmail(String email) {
        Person personByEmail = Optional.ofNullable(personRepository.findByEmail(email)).
                orElseThrow(() -> new PersonNotFoundException("Such person is not found"));
        return modelMapper.map(personRepository.save(personByEmail), PersonInfoDto.class);
    }

    @Override
    public List<PersonInfoDto> getPersonsOlderThan(int age) {
        return personRepository.findByAgeGreaterThan(age).stream()
                .map(person -> modelMapper.map(person, PersonInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonInfoDto> getPersonsByMarriageStatus(boolean isMarried) {
        return personRepository.findByMarried(isMarried).stream()
                .map(person -> modelMapper.map(person, PersonInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonInfoDto> getAll() {
        return personRepository.findAll().stream()
                .map(person -> modelMapper.map(person, PersonInfoDto.class))
                .collect(Collectors.toList());
    }


}
