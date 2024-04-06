package com.carlos.person_management_app.services;

import com.carlos.person_management_app.adapters.Adapter;
import com.carlos.person_management_app.business.entities.Address;
import com.carlos.person_management_app.business.entities.Person;
import com.carlos.person_management_app.dtos.AddressCreateDTO;
import com.carlos.person_management_app.dtos.AddressDTO;
import com.carlos.person_management_app.models.AddressModel;
import com.carlos.person_management_app.models.PersonModel;
import com.carlos.person_management_app.repositories.address.AddressRepository;
import com.carlos.person_management_app.repositories.address.AddressRepositoryImpl;
import com.carlos.person_management_app.repositories.person.PersonRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final Adapter adapter;
    private final AddressRepository addressRepository;
    private final AddressRepositoryImpl addressRepositoryImpl;
    private final PersonRepositoryImpl personRepositoryImpl;

    public AddressDTO create(AddressCreateDTO addressDTO, Long personId) {
        PersonModel personModel = personRepositoryImpl.findById(personId);
        Person personEntity = adapter.mapSourceToTarget(personModel, Person.class);
        Address addressEntity = adapter.mapSourceToTarget(addressDTO, Address.class);
        addressEntity.setPerson(personEntity);
        AddressModel addressModel = adapter.mapSourceToTarget(addressEntity, AddressModel.class);
        addressRepository.save(addressModel);
        return adapter.mapSourceToTarget(addressModel, AddressDTO.class);
    }
}
