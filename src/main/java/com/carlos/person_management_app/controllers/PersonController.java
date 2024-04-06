package com.carlos.person_management_app.controllers;

import com.carlos.person_management_app.dtos.person.PersonCreateDTO;
import com.carlos.person_management_app.dtos.person.PersonDTO;
import com.carlos.person_management_app.dtos.person.PersonEditDTO;
import com.carlos.person_management_app.exceptions.CannotDeleteException;
import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.services.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonService personService;

    @PostMapping()
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonCreateDTO personCreateDTO) throws NotFoundException {
        PersonDTO personDTO = personService.create(personCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @Valid @RequestBody PersonEditDTO personEditDTO) throws NotFoundException {
        PersonDTO personDTO = personService.update(id, personEditDTO);
        return ResponseEntity.status(HttpStatus.OK).body(personDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) throws CannotDeleteException, NotFoundException {
        personService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping()
    public ResponseEntity<List<PersonDTO>> listAllPersons() throws NotFoundException {
        List<PersonDTO> personDTOList = personService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(personDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findPersonById(@PathVariable Long id) throws NotFoundException {
        PersonDTO personDTO = personService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(personDTO);
    }
}
