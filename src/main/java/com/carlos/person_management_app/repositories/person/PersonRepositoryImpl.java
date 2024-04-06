package com.carlos.person_management_app.repositories.person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;
}
