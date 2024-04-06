package com.carlos.person_management_app.controllers;

import com.carlos.person_management_app.dtos.PersonCreateDTO;
import com.carlos.person_management_app.dtos.PersonDTO;
import com.carlos.person_management_app.services.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonService personService;

    @PostMapping()
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonCreateDTO personCreateDTO)  {
        PersonDTO personDTO = personService.create(personCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personDTO);
    }
}
