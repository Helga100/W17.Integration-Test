package com.example.hw16nosqldb.web;

import com.example.hw16nosqldb.person_dto.NewPersonDto;
import com.example.hw16nosqldb.person_dto.PersonInfoDto;
import com.example.hw16nosqldb.person_service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonInfoDto> createPerson(@RequestBody @Validated NewPersonDto newPersonDto) {
        if (newPersonDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personService.create(newPersonDto), HttpStatus.CREATED);
    }

    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<PersonInfoDto>> getPersonsByFirstName(@PathVariable("firstName") @Validated String firstName) {
        return new ResponseEntity<>(personService.getByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<PersonInfoDto>> getPersonsByLastName(@PathVariable("lastName") @Validated String lastName) {
        return new ResponseEntity<>(personService.getByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PersonInfoDto> getPersonByEmail(@PathVariable("email") @Validated String email) {
        return new ResponseEntity<>(personService.getByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<PersonInfoDto>> getPersonsOlderThan(@PathVariable("age") Optional<Integer> age) {
        int ageOfAdult = age.orElse(18);
        return new ResponseEntity<>(personService.getPersonsOlderThan(ageOfAdult), HttpStatus.OK);
    }

    @GetMapping("/marriage/{isMarried}")
    public ResponseEntity<List<PersonInfoDto>> getPersonsByMarriageStatus(@PathVariable("isMarried") boolean isMarried) {
        return new ResponseEntity<>(personService.getPersonsByMarriageStatus(isMarried), HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<List<PersonInfoDto>> getAllPersons() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }
}
