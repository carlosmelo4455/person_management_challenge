package com.carlos.person_management_app.repositories.address;

import com.carlos.person_management_app.exceptions.NotFoundException;
import com.carlos.person_management_app.models.AddressModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public AddressModel findById(Long id) throws NotFoundException {
        TypedQuery<AddressModel> query = entityManager.createQuery("SELECT a FROM AddressModel a WHERE a.id = :id", AddressModel.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            throw NotFoundException.create("Address", "id", "Não encontrado.");
        }
    }

    public void deleteById(Long id) throws NotFoundException {
        TypedQuery<AddressModel> query = entityManager.createQuery("SELECT a FROM AddressModel a WHERE a.id = :id", AddressModel.class);
        query.setParameter("id", id);
        AddressModel addressModel;
        try {
            addressModel = query.getSingleResult();
        } catch (Exception e) {
            throw NotFoundException.create("Address", "id", "Não encontrado.");
        }
        entityManager.remove(addressModel);
    }

    public List<AddressModel> findAll() throws NotFoundException {
        TypedQuery<AddressModel> query = entityManager.createQuery("SELECT a FROM AddressModel a", AddressModel.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw NotFoundException.create("Address", "id", "Nenhum endereço encontrado");
        }
    }

    public List<AddressModel> findByPersonId(Long personId) throws NotFoundException {
        TypedQuery<AddressModel> query = entityManager.createQuery("SELECT a FROM AddressModel a WHERE a.person.id = :personId", AddressModel.class);
        query.setParameter("personId", personId);
        try {
            return query.getResultList();
        } catch (Exception e) {
            throw NotFoundException.create("Address", "id", "Nenhum endereço encontrado para o usuário");
        }
    }
}
