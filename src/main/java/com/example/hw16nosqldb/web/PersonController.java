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

    @GetMapping("/firstName")
    public ResponseEntity<List<PersonInfoDto>> getPersonsByFirstName(@RequestParam("firstName") String firstName) {
        return new ResponseEntity<>(personService.getByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/lastName")
    public ResponseEntity<List<PersonInfoDto>> getPersonsByLastName(@RequestParam("lastName")  String lastName) {
        return new ResponseEntity<>(personService.getByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<PersonInfoDto> getPersonByEmail(@RequestParam("email") String email) {
        if (email == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personService.getByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/age")
    public ResponseEntity<List<PersonInfoDto>> getPersonsOlderThan(@RequestParam("age") Optional<Integer> age) {
        int ageOfAdult = age.orElse(18);
        return new ResponseEntity<>(personService.getPersonsOlderThan(ageOfAdult), HttpStatus.OK);
    }

    @GetMapping("/marriage")
    public ResponseEntity<List<PersonInfoDto>> getPersonsByMarriageStatus(@RequestParam("married") boolean isMarried) {
        return new ResponseEntity<>(personService.getPersonsByMarriageStatus(isMarried), HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<List<PersonInfoDto>> getAllPersons() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }
}
