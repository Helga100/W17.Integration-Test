package com.example.hw16nosqldb.service_unit_test;

import com.example.hw16nosqldb.model.Person;
import com.example.hw16nosqldb.person_dto.PersonInfoDto;
import com.example.hw16nosqldb.person_repository.PersonRepository;
import com.example.hw16nosqldb.person_service.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    ModelMapper modelMapper;
    private PersonServiceImpl personService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        personService = new PersonServiceImpl(modelMapper, personRepository);
    }

    @Test
    public void checkReturnEmptyPersonList() {
        when(personRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        assertEquals(0, personService.getAll().size());
        verify(personRepository, times(1)).findAll();  // перевыряю чи findAll з personRepository
        verify(modelMapper, never()).map(any(), any());
    }

    @Test
    public void checkPersonsName() {
        List<Person> persons = new ArrayList<>();
        PersonInfoDto personInfoDto = new PersonInfoDto();
        persons.add(Person.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .age(15)
                .email("Ivanov@gmail.com")
                .married(true)
                .build());
        persons.add(Person.builder()
                .firstName("Oleg")
                .lastName("Petrov")
                .age(45)
                .email("Petrov@gmail.com")
                .married(false)
                .build());
        persons.add(Person.builder()
                .firstName("Stepan")
                .lastName("Stepanov")
                .age(95)
                .email("Stepanov@gmail.com")
                .married(true)
                .build());
        when(personRepository.findAll()).thenReturn(persons);  // для того щоб задати поведінку методу замоканого класу
        when(modelMapper.map(any(), eq(PersonInfoDto.class))).thenReturn(personInfoDto);

        List<PersonInfoDto> getAllPersons = personService.getAll();

        assertEquals(3, getAllPersons.size());

        verify(personRepository).findAll();
        verify(modelMapper, times(3)).map(any(), eq(PersonInfoDto.class));

    }
}
