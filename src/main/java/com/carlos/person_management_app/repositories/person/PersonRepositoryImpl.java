package com.carlos.person_management_app.repositories.person;

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

    public PersonModel findById(Long id) {
        TypedQuery<PersonModel> query = entityManager.createQuery("SELECT p FROM PersonModel p WHERE p.id = :id", PersonModel.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public void deleteById(Long id) {
        TypedQuery<PersonModel> query = entityManager.createQuery("SELECT p FROM PersonModel p WHERE p.id = :id", PersonModel.class);
        query.setParameter("id", id);
        PersonModel personModel = query.getSingleResult();
        entityManager.remove(personModel);
    }

    public List<PersonModel> findAll() {
        TypedQuery<PersonModel> query = entityManager.createQuery("SELECT p FROM PersonModel p", PersonModel.class);
        return query.getResultList();
    }
}