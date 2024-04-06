package com.carlos.person_management_app.services;

import com.carlos.person_management_app.adapters.Adapter;
import com.carlos.person_management_app.business.entities.Person;
import com.carlos.person_management_app.dtos.person.PersonCreateDTO;
import com.carlos.person_management_app.dtos.person.PersonDTO;
import com.carlos.person_management_app.dtos.person.PersonEditDTO;
import com.carlos.person_management_app.exceptions.CannotDeleteException;
import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.models.PersonModel;
import com.carlos.person_management_app.repositories.person.PersonRepository;
import com.carlos.person_management_app.repositories.person.PersonRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final Adapter adapter;
    private final PersonRepository personRepository;
    private final PersonRepositoryImpl personRepositoryImpl;
    private final AddressService addressService;

    public PersonDTO create(PersonCreateDTO personCreateDTO) throws NotFoundException {
        PersonModel personModel = adapter.mapSourceToTarget(personCreateDTO, PersonModel.class);
        personRepository.save(personModel);
        addressService.create(personCreateDTO.getAddress(), personModel.getId());
        PersonModel personModelUpdated = personRepositoryImpl.findById(personModel.getId());
        return adapter.mapSourceToTarget(personModelUpdated, PersonDTO.class);
    }

    public PersonDTO update(Long id, PersonEditDTO personEditDTO) throws NotFoundException {
        PersonModel personModel = personRepositoryImpl.findById(id);
        Person persistedPerson = adapter.mapSourceToTarget(personModel, Person.class);
        Person modifiedPerson = adapter.mapSourceToTarget(personEditDTO, Person.class);
        adapter.updateTargetWithSource(modifiedPerson, persistedPerson);
        PersonModel updatedPersonModel = adapter.mapSourceToTarget(persistedPerson, PersonModel.class);
        personRepository.save(updatedPersonModel);
        return adapter.mapSourceToTarget(persistedPerson, PersonDTO.class);
    }

    @Transactional
    public void delete(Long id) throws CannotDeleteException, NotFoundException {
        personRepositoryImpl.deleteById(id);
    }

    public PersonDTO findById(Long id) throws NotFoundException {
        PersonModel personModel = personRepositoryImpl.findById(id);
        Person person = adapter.mapSourceToTarget(personModel, Person.class);
        return adapter.mapSourceToTarget(person, PersonDTO.class);
    }

    public List<PersonDTO> findAll() throws NotFoundException {
        List<PersonModel> personModels = personRepositoryImpl.findAll();
        return personModels.stream()
                .map(personModel -> adapter.mapSourceToTarget(personModel, PersonDTO.class))
                .collect(Collectors.toList());
    }
}