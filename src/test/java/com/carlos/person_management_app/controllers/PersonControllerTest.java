
package com.carlos.person_management_app.controllers;

import com.carlos.person_management_app.dtos.person.PersonCreateDTO;
import com.carlos.person_management_app.dtos.person.PersonDTO;
import com.carlos.person_management_app.dtos.person.PersonEditDTO;
import com.carlos.person_management_app.exceptions.CannotDeleteException;
import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    @BeforeEach
    public void setup() throws NotFoundException {
        MockitoAnnotations.openMocks(this);
        when(personService.create(any(PersonCreateDTO.class))).thenReturn(new PersonDTO());
        when(personService.update(eq(1L), any(PersonEditDTO.class))).thenReturn(new PersonDTO());
        when(personService.findById(eq(1L))).thenReturn(new PersonDTO());
        when(personService.findAll()).thenReturn(Collections.singletonList(new PersonDTO()));
    }

    @Test
    public void createPersonHappyPath() throws NotFoundException {
        ResponseEntity<PersonDTO> response = personController.createPerson(new PersonCreateDTO());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void updatePersonHappyPath() throws NotFoundException {
        ResponseEntity<PersonDTO> response = personController.updatePerson(1L, new PersonEditDTO());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deletePersonHappyPath() throws CannotDeleteException, NotFoundException {
        ResponseEntity<Void> response = personController.deletePerson(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void findPersonByIdHappyPath() throws NotFoundException {
        ResponseEntity<PersonDTO> response = personController.findPersonById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void listAllPersonsHappyPath() throws NotFoundException {
        ResponseEntity<List<PersonDTO>> response = personController.listAllPersons();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}