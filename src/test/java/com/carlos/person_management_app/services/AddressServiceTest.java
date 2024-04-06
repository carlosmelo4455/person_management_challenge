package com.carlos.person_management_app.services;

import com.carlos.person_management_app.adapters.Adapter;
import com.carlos.person_management_app.business.entities.Address;
import com.carlos.person_management_app.business.entities.Person;
import com.carlos.person_management_app.dtos.address.AddressCreateDTO;
import com.carlos.person_management_app.dtos.address.AddressEditDTO;
import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.models.AddressModel;
import com.carlos.person_management_app.models.PersonModel;
import com.carlos.person_management_app.repositories.address.AddressRepository;
import com.carlos.person_management_app.repositories.address.AddressRepositoryImpl;
import com.carlos.person_management_app.repositories.person.PersonRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressRepositoryImpl addressRepositoryImpl;

    @Mock
    private PersonRepositoryImpl personRepositoryImpl;

    @Mock
    private Adapter adapter;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createReturnsAddressDTOWhenAddressIsCreated() throws NotFoundException {
        AddressCreateDTO addressCreateDTO = new AddressCreateDTO();
        PersonModel personModel = new PersonModel();
        AddressModel addressModel = new AddressModel();
        Address addressEntity = new Address();
        Person personEntity = new Person();

        when(personRepositoryImpl.findById(anyLong())).thenReturn(personModel);
        when(adapter.mapSourceToTarget(personModel, Person.class)).thenReturn(personEntity);
        when(adapter.mapSourceToTarget(addressCreateDTO, Address.class)).thenReturn(addressEntity);
        when(adapter.mapSourceToTarget(addressEntity, AddressModel.class)).thenReturn(addressModel);
        when(addressRepository.save(any(AddressModel.class))).thenReturn(addressModel);

        addressService.create(addressCreateDTO, 1L);

        verify(addressRepository, times(1)).save(any(AddressModel.class));
    }

    @Test
    public void createThrowsNotFoundExceptionWhenPersonNotFound() throws NotFoundException {
        AddressCreateDTO addressCreateDTO = new AddressCreateDTO();

        when(personRepositoryImpl.findById(anyLong())).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> addressService.create(addressCreateDTO, 1L));
    }

    @Test
    public void updateReturnsAddressDTOWhenAddressIsUpdated() throws NotFoundException {
        AddressEditDTO addressEditDTO = new AddressEditDTO();
        AddressModel addressModel = new AddressModel();
        Address persistedAddress = new Address();
        Address modifiedAddress = new Address();

        when(addressRepositoryImpl.findById(anyLong())).thenReturn(addressModel);
        when(adapter.mapSourceToTarget(addressModel, Address.class)).thenReturn(persistedAddress);
        when(adapter.mapSourceToTarget(addressEditDTO, Address.class)).thenReturn(modifiedAddress);
        when(adapter.mapSourceToTarget(persistedAddress, AddressModel.class)).thenReturn(addressModel);
        when(addressRepository.save(any(AddressModel.class))).thenReturn(addressModel);

        addressService.update(addressEditDTO, 1L);

        verify(addressRepository, times(1)).save(any(AddressModel.class));
    }

    @Test
    public void updateThrowsNotFoundExceptionWhenAddressNotFound() throws NotFoundException {
        AddressEditDTO addressEditDTO = new AddressEditDTO();

        when(addressRepositoryImpl.findById(anyLong())).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> addressService.update(addressEditDTO, 1L));
    }

    @Test
    public void deleteRemovesAddressWhenAddressExists() throws NotFoundException {
        AddressModel addressModel = new AddressModel();
        PersonModel personModel = new PersonModel();
        personModel.setAddresses(new ArrayList<>()); // Initialize the addresses field
        addressModel.setPerson(personModel);

        when(addressRepositoryImpl.findById(anyLong())).thenReturn(addressModel);

        addressService.delete(1L);

        verify(addressRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void deleteThrowsNotFoundExceptionWhenAddressNotFound() throws NotFoundException {
        when(addressRepositoryImpl.findById(anyLong())).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> addressService.delete(1L));
    }

    @Test
    public void findByIdReturnsAddressDTOWhenAddressExists() throws NotFoundException {
        AddressModel addressModel = new AddressModel();

        when(addressRepositoryImpl.findById(anyLong())).thenReturn(addressModel);

        addressService.findById(1L);

        verify(addressRepositoryImpl, times(1)).findById(anyLong());
    }

    @Test
    public void findByIdThrowsNotFoundExceptionWhenAddressNotFound() throws NotFoundException {
        when(addressRepositoryImpl.findById(anyLong())).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> addressService.findById(1L));
    }

    @Test
    public void findByPersonIdReturnsAddressDTOListWhenAddressesExist() throws NotFoundException {
        when(addressRepositoryImpl.findByPersonId(anyLong())).thenReturn(anyList());

        addressService.findByPersonId(1L);

        verify(addressRepositoryImpl, times(1)).findByPersonId(anyLong());
    }

    @Test
    public void findByPersonIdThrowsNotFoundExceptionWhenAddressesNotFound() throws NotFoundException {
        when(addressRepositoryImpl.findByPersonId(anyLong())).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> addressService.findByPersonId(1L));
    }
}