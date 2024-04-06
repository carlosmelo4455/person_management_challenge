package com.carlos.person_management_app.repositories.address;

import com.carlos.person_management_app.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {

}
