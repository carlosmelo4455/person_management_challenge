package com.carlos.person_management_app.repositories.person;

import com.carlos.person_management_app.exceptions.CannotDeleteException;
import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.models.PersonModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public PersonModel findById(Long id) throws NotFoundException {
        TypedQuery<PersonModel> query = entityManager.createQuery("SELECT p FROM PersonModel p WHERE p.id = :id", PersonModel.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            throw NotFoundException.create("Person", "id", "Não encontrado.");
        }
    }

    public void deleteById(Long id) throws CannotDeleteException, NotFoundException {
        TypedQuery<PersonModel> query = entityManager.createQuery("SELECT p FROM PersonModel p WHERE p.id = :id", PersonModel.class);
        query.setParameter("id", id);
        PersonModel personModel;
        try {
            personModel = query.getSingleResult();
        } catch (Exception e) {
            throw NotFoundException.create("Person", "id", "Não encontrado.");
        }
        try {
            entityManager.remove(personModel);
        } catch (Exception e) {
            throw CannotDeleteException.create("Person", "id", "Não foi possível deletar a pessoa com id: " + id);
        }
    }

    public List<PersonModel> findAll() throws NotFoundException {
        TypedQuery<PersonModel> query = entityManager.createQuery("SELECT p FROM PersonModel p", PersonModel.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw NotFoundException.create("Person", "id", "Nada Foi encontrado.");
        }
    }
}