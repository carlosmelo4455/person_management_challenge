package com.carlos.person_management_app.services;

import com.carlos.person_management_app.adapters.Adapter;
import com.carlos.person_management_app.business.entities.Address;
import com.carlos.person_management_app.business.entities.Person;
import com.carlos.person_management_app.dtos.address.AddressCreateDTO;
import com.carlos.person_management_app.dtos.address.AddressDTO;
import com.carlos.person_management_app.dtos.address.AddressEditDTO;
import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.models.AddressModel;
import com.carlos.person_management_app.models.PersonModel;
import com.carlos.person_management_app.repositories.address.AddressRepository;
import com.carlos.person_management_app.repositories.address.AddressRepositoryImpl;
import com.carlos.person_management_app.repositories.person.PersonRepository;
import com.carlos.person_management_app.repositories.person.PersonRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final Adapter adapter;
    private final AddressRepository addressRepository;
    private final AddressRepositoryImpl addressRepositoryImpl;
    private final PersonRepositoryImpl personRepositoryImpl;
    private final PersonRepository personRepository;

    public AddressDTO create(AddressCreateDTO addressDTO, Long personId) throws NotFoundException {
        PersonModel personModel = personRepositoryImpl.findById(personId);
        Person personEntity = adapter.mapSourceToTarget(personModel, Person.class);
        Address addressEntity = adapter.mapSourceToTarget(addressDTO, Address.class);
        addressEntity.setPerson(personEntity);
        AddressModel addressModel = adapter.mapSourceToTarget(addressEntity, AddressModel.class);
        addressRepository.save(addressModel);
        personEntity.addAddress(addressEntity);
        PersonModel personModelUpdated = adapter.mapSourceToTarget(personEntity, PersonModel.class);
        if (addressModel.isMain()) {
            for (AddressModel address : personModelUpdated.getAddresses()) {
                if (!Objects.equals(address.getId(), addressModel.getId())) {
                    address.setMain(false);
                }
            }
            personModelUpdated.setMainAddress(addressModel);
        }
        personRepository.save(personModelUpdated);
        return adapter.mapSourceToTarget(addressModel, AddressDTO.class);
    }

    public AddressDTO update(AddressEditDTO addressDTO, Long id) throws NotFoundException {
        AddressModel addressModel = addressRepositoryImpl.findById(id);
        Address PersistedAddress = adapter.mapSourceToTarget(addressModel, Address.class);
        Address ModifiedAddress = adapter.mapSourceToTarget(addressDTO, Address.class);
        adapter.updateTargetWithSource(ModifiedAddress, PersistedAddress);
        AddressModel updatedAddressModel = adapter.mapSourceToTarget(PersistedAddress, AddressModel.class);
        addressRepository.save(updatedAddressModel);
        return adapter.mapSourceToTarget(updatedAddressModel, AddressDTO.class);
    }

    @Transactional
    public void delete(Long id) throws NotFoundException {
        AddressModel addressModel = addressRepositoryImpl.findById(id);
        PersonModel personModel = addressModel.getPerson();
        personModel.getAddresses().remove(addressModel);
        personRepository.save(personModel);
        addressRepository.deleteById(id);
    }

    public AddressDTO findById(Long id) throws NotFoundException {
        AddressModel addressModel = addressRepositoryImpl.findById(id);
        return adapter.mapSourceToTarget(addressModel, AddressDTO.class);
    }

    public List<AddressDTO> findByPersonId(Long personId) throws NotFoundException {
        List<AddressModel> addressModels = addressRepositoryImpl.findByPersonId(personId);
        return addressModels.stream()
                .map(addressModel -> adapter.mapSourceToTarget(addressModel, AddressDTO.class))
                .toList();
    }
}
