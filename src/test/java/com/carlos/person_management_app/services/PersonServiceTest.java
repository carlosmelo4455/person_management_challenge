package com.carlos.person_management_app.services;

import com.carlos.person_management_app.adapters.Adapter;
import com.carlos.person_management_app.dtos.address.AddressCreateDTO;
import com.carlos.person_management_app.dtos.person.PersonCreateDTO;
import com.carlos.person_management_app.dtos.person.PersonEditDTO;
import com.carlos.person_management_app.exceptions.CannotDeleteException;
import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.models.PersonModel;
import com.carlos.person_management_app.repositories.person.PersonRepository;
import com.carlos.person_management_app.repositories.person.PersonRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonRepositoryImpl personRepositoryImpl;

    @Mock
    private Adapter adapter;

    @Mock
    private AddressService addressService;

    @BeforeEach
    public void setup() throws NotFoundException {
        MockitoAnnotations.openMocks(this);
        when(adapter.mapSourceToTarget(any(PersonCreateDTO.class), eq(PersonModel.class))).thenReturn(new PersonModel());
        when(addressService.create(any(AddressCreateDTO.class), any(Long.class))).thenReturn(null);
    }

    @Test
    public void createPersonHappyPath() throws NotFoundException {
        PersonCreateDTO personCreateDTO = new PersonCreateDTO();
        PersonModel personModel = new PersonModel();
        when(personRepository.save(any(PersonModel.class))).thenReturn(personModel);
        when(personRepositoryImpl.findById(any(Long.class))).thenReturn(personModel);
        personService.create(personCreateDTO);
        verify(personRepository, times(1)).save(any(PersonModel.class));
    }

    @Test
    public void updatePersonHappyPath() throws NotFoundException {
        PersonEditDTO personEditDTO = new PersonEditDTO();
        PersonModel personModel = new PersonModel();
        when(personRepositoryImpl.findById(any(Long.class))).thenReturn(personModel);
        when(adapter.mapSourceToTarget(any(PersonEditDTO.class), eq(PersonModel.class))).thenReturn(personModel);
        when(personRepository.save(any(PersonModel.class))).thenReturn(personModel);
        personService.update(1L, personEditDTO);
        verify(personRepository, times(1)).save(any(PersonModel.class));
    }

    @Test
    public void deletePersonHappyPath() throws CannotDeleteException, NotFoundException {
        doNothing().when(personRepositoryImpl).deleteById(any(Long.class));
        personService.delete(1L);
        verify(personRepositoryImpl, times(1)).deleteById(any(Long.class));
    }

    @Test
    public void findByIdHappyPath() throws NotFoundException {
        PersonModel personModel = new PersonModel();
        when(personRepositoryImpl.findById(any(Long.class))).thenReturn(personModel);
        personService.findById(1L);
        verify(personRepositoryImpl, times(1)).findById(any(Long.class));
    }

    @Test
    public void createPersonNotFound() throws NotFoundException {
        PersonCreateDTO personCreateDTO = new PersonCreateDTO();
        when(personRepositoryImpl.findById(any(Long.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> personService.create(personCreateDTO));
    }

    @Test
    public void updatePersonNotFound() throws NotFoundException {
        PersonEditDTO personEditDTO = new PersonEditDTO();
        when(personRepositoryImpl.findById(any(Long.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> personService.update(1L, personEditDTO));
    }

    @Test
    public void deletePersonNotFound() throws CannotDeleteException, NotFoundException {
        doThrow(NotFoundException.class).when(personRepositoryImpl).deleteById(any(Long.class));
        assertThrows(NotFoundException.class, () -> personService.delete(1L));
    }

    @Test
    public void findByIdNotFound() throws NotFoundException {
        when(personRepositoryImpl.findById(any(Long.class))).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> personService.findById(1L));
    }
}