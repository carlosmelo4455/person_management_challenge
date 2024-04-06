package com.carlos.person_management_app.controllers;

import com.carlos.person_management_app.dtos.address.AddressCreateDTO;
import com.carlos.person_management_app.dtos.address.AddressDTO;
import com.carlos.person_management_app.dtos.address.AddressEditDTO;
import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.services.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/persons")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/{personId}/addresses")
    public ResponseEntity<AddressDTO> createAddress(@PathVariable Long personId, @Valid @RequestBody AddressCreateDTO addressDTO) throws NotFoundException {
        AddressDTO newAddress = addressService.create(addressDTO, personId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
    }

    @PutMapping("/{personId}/addresses/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressEditDTO addressDTO) throws NotFoundException {
        AddressDTO updatedAddress = addressService.update(addressDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAddress);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) throws NotFoundException {
        addressService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<AddressDTO> findAddressById(@PathVariable Long id) throws NotFoundException {
        AddressDTO addressDTO = addressService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(addressDTO);
    }

    @GetMapping("/{personId}/addresses")
    public ResponseEntity<List<AddressDTO>> findAddressByPersonId(@PathVariable Long personId) throws NotFoundException {
        List<AddressDTO> addressDTOList = addressService.findByPersonId(personId);
        return ResponseEntity.status(HttpStatus.OK).body(addressDTOList);
    }
}
