package com.carlos.person_management_app.repositories.address;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;
}
