package com.carlos.person_management_app.repositories;

import com.carlos.person_management_app.exceptions.CannotDeleteException;
import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.models.PersonModel;
import com.carlos.person_management_app.repositories.person.PersonRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PersonRepositoryImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<PersonModel> query;

    @InjectMocks
    private PersonRepositoryImpl personRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdReturnsPersonModelWhenIdExists() throws NotFoundException {
        PersonModel expectedPerson = new PersonModel();
        when(entityManager.createQuery(anyString(), eq(PersonModel.class))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(expectedPerson);

        PersonModel actualPerson = personRepository.findById(1L);

        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void findByIdThrowsNotFoundExceptionWhenIdDoesNotExist() {
        when(entityManager.createQuery(anyString(), eq(PersonModel.class))).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new RuntimeException());

        assertThrows(NotFoundException.class, () -> personRepository.findById(1L));
    }

    @Test
    public void findAllReturnsPersonModelListWhenPersonsExist() throws NotFoundException {
        List<PersonModel> expectedPersons = Collections.singletonList(new PersonModel());
        when(entityManager.createQuery(anyString(), eq(PersonModel.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedPersons);

        List<PersonModel> actualPersons = personRepository.findAll();

        assertEquals(expectedPersons, actualPersons);
    }

    @Test
    public void findAllThrowsNotFoundExceptionWhenNoPersonsExist() {
        when(entityManager.createQuery(anyString(), eq(PersonModel.class))).thenReturn(query);
        when(query.getResultList()).thenThrow(new RuntimeException());

        assertThrows(NotFoundException.class, () -> personRepository.findAll());
    }

    @Test
    public void deleteByIdRemovesPersonModelWhenIdExists() throws NotFoundException, CannotDeleteException {
        PersonModel personModel = new PersonModel();
        when(entityManager.createQuery(anyString(), eq(PersonModel.class))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(personModel);

        personRepository.deleteById(1L);

        verify(entityManager, times(1)).remove(personModel);
    }

    @Test
    public void deleteByIdThrowsNotFoundExceptionWhenIdDoesNotExist() {
        when(entityManager.createQuery(anyString(), eq(PersonModel.class))).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new RuntimeException());

        assertThrows(NotFoundException.class, () -> personRepository.deleteById(1L));
    }

    @Test
    public void deleteByIdThrowsCannotDeleteExceptionWhenDeleteFails()  {
        PersonModel personModel = new PersonModel();
        when(entityManager.createQuery(anyString(), eq(PersonModel.class))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(personModel);
        doThrow(new RuntimeException()).when(entityManager).remove(any());

        assertThrows(CannotDeleteException.class, () -> personRepository.deleteById(1L));
    }
}