package com.carlos.person_management_app.controllers;

import com.carlos.person_management_app.dtos.address.AddressCreateDTO;
import com.carlos.person_management_app.dtos.address.AddressDTO;
import com.carlos.person_management_app.dtos.address.AddressEditDTO;
import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.services.AddressService;
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

public class AddressControllerTest {

    @InjectMocks
    private AddressController addressController;

    @Mock
    private AddressService addressService;

    @BeforeEach
    public void setup() throws NotFoundException {
        MockitoAnnotations.openMocks(this);
        when(addressService.create(any(AddressCreateDTO.class), eq(1L))).thenReturn(new AddressDTO());
        when(addressService.update(any(AddressEditDTO.class), eq(1L))).thenReturn(new AddressDTO());
        when(addressService.findById(eq(1L))).thenReturn(new AddressDTO());
        when(addressService.findByPersonId(eq(1L))).thenReturn(Collections.singletonList(new AddressDTO()));
    }

    @Test
    public void createAddressHappyPath() throws NotFoundException {
        ResponseEntity<AddressDTO> response = addressController.createAddress(1L, new AddressCreateDTO());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void updateAddressHappyPath() throws NotFoundException {
        ResponseEntity<AddressDTO> response = addressController.updateAddress(1L, new AddressEditDTO());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteAddressHappyPath() throws NotFoundException {
        ResponseEntity<Void> response = addressController.deleteAddress(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void findAddressByIdHappyPath() throws NotFoundException {
        ResponseEntity<AddressDTO> response = addressController.findAddressById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void findAddressByPersonIdHappyPath() throws NotFoundException {
        ResponseEntity<List<AddressDTO>> response = addressController.findAddressByPersonId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}