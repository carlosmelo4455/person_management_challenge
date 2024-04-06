package com.carlos.person_management_app.services;

import com.carlos.person_management_app.adapters.Adapter;
import com.carlos.person_management_app.business.entities.Address;
import com.carlos.person_management_app.business.entities.Person;
import com.carlos.person_management_app.dtos.AddressDTO;
import com.carlos.person_management_app.dtos.PersonCreateDTO;
import com.carlos.person_management_app.dtos.PersonDTO;
import com.carlos.person_management_app.dtos.PersonEditDTO;
import com.carlos.person_management_app.models.PersonModel;
import com.carlos.person_management_app.repositories.address.AddressRepositoryImpl;
import com.carlos.person_management_app.repositories.person.PersonRepository;
import com.carlos.person_management_app.repositories.person.PersonRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final Adapter adapter;
    private final PersonRepository personRepository;
    private final PersonRepositoryImpl personRepositoryImpl;
    private final AddressRepositoryImpl addressRepositoryImpl;
    private final AddressService addressService;

    public PersonDTO create(PersonCreateDTO personCreateDTO) {
        Person personEntity = adapter.mapSourceToTarget(personCreateDTO, Person.class);
        PersonModel personModel = adapter.mapSourceToTarget(personEntity, PersonModel.class);
        personRepository.save(personModel);
        AddressDTO addressDTO = addressService.create(personCreateDTO.getAddress(), personModel.getId());
        Person savedEntity = adapter.mapSourceToTarget(personModel, Person.class);
        savedEntity.addAddress(adapter.mapSourceToTarget(addressDTO, Address.class));
        PersonModel personModelUpdated = adapter.mapSourceToTarget(savedEntity, PersonModel.class);
        personRepository.save(personModelUpdated);
        return adapter.mapSourceToTarget(personModelUpdated, PersonDTO.class);
    }

    public PersonDTO update(Long id, PersonEditDTO personEditDTO) {
        PersonModel personModel = personRepositoryImpl.findById(id);
        Person persistedPerson = adapter.mapSourceToTarget(personModel, Person.class);
        Person modifiedPerson = adapter.mapSourceToTarget(personEditDTO, Person.class);
        adapter.updateTargetWithSource(modifiedPerson, persistedPerson);
        personRepository.save(adapter.mapSourceToTarget(persistedPerson, PersonModel.class));
        return adapter.mapSourceToTarget(persistedPerson, PersonDTO.class);
    }

    public void delete(Long id) {
        personRepositoryImpl.deleteById(id);
    }

    public PersonDTO findById(Long id) {
        PersonModel personModel = personRepositoryImpl.findById(id);
        Person person = adapter.mapSourceToTarget(personModel, Person.class);
        return adapter.mapSourceToTarget(person, PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        List<PersonModel> personModels = personRepositoryImpl.findAll();
        return personModels.stream()
                .map(personModel -> adapter.mapSourceToTarget(personModel, PersonDTO.class))
                .collect(Collectors.toList());
    }
}