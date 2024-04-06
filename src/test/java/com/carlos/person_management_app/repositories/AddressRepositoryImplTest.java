package com.carlos.person_management_app.repositories;

import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.models.AddressModel;
import com.carlos.person_management_app.repositories.address.AddressRepositoryImpl;
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
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddressRepositoryImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<AddressModel> query;

    @InjectMocks
    private AddressRepositoryImpl addressRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdReturnsAddressModelWhenIdExists() throws NotFoundException {
        AddressModel expectedAddress = new AddressModel();
        when(entityManager.createQuery(anyString(), eq(AddressModel.class))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(expectedAddress);

        AddressModel actualAddress = addressRepository.findById(1L);

        assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void findByIdThrowsNotFoundExceptionWhenIdDoesNotExist() {
        when(entityManager.createQuery(anyString(), eq(AddressModel.class))).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new RuntimeException());

        assertThrows(NotFoundException.class, () -> addressRepository.findById(1L));
    }

    @Test
    public void findAllReturnsAddressModelListWhenAddressesExist() throws NotFoundException {
        List<AddressModel> expectedAddresses = Collections.singletonList(new AddressModel());
        when(entityManager.createQuery(anyString(), eq(AddressModel.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedAddresses);

        List<AddressModel> actualAddresses = addressRepository.findAll();

        assertEquals(expectedAddresses, actualAddresses);
    }

    @Test
    public void findAllThrowsNotFoundExceptionWhenNoAddressesExist() {
        when(entityManager.createQuery(anyString(), eq(AddressModel.class))).thenReturn(query);
        when(query.getResultList()).thenThrow(new RuntimeException());

        assertThrows(NotFoundException.class, () -> addressRepository.findAll());
    }

    @Test
    public void findByPersonIdReturnsAddressModelListWhenPersonIdExists() throws NotFoundException {
        List<AddressModel> expectedAddresses = Collections.singletonList(new AddressModel());
        when(entityManager.createQuery(anyString(), eq(AddressModel.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedAddresses);

        List<AddressModel> actualAddresses = addressRepository.findByPersonId(1L);

        assertEquals(expectedAddresses, actualAddresses);
    }

    @Test
    public void findByPersonIdThrowsNotFoundExceptionWhenPersonIdDoesNotExist() {
        when(entityManager.createQuery(anyString(), eq(AddressModel.class))).thenReturn(query);
        when(query.getResultList()).thenThrow(new RuntimeException());

        assertThrows(NotFoundException.class, () -> addressRepository.findByPersonId(1L));
    }

    @Test
    public void deleteByIdRemovesAddressModelWhenIdExists() throws NotFoundException {
        AddressModel addressModel = new AddressModel();
        when(entityManager.createQuery(anyString(), eq(AddressModel.class))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(addressModel);

        addressRepository.deleteById(1L);

        verify(entityManager, times(1)).remove(addressModel);
    }

    @Test
    public void deleteByIdThrowsNotFoundExceptionWhenIdDoesNotExist() {
        when(entityManager.createQuery(anyString(), eq(AddressModel.class))).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new RuntimeException());

        assertThrows(NotFoundException.class, () -> addressRepository.deleteById(1L));
    }
}