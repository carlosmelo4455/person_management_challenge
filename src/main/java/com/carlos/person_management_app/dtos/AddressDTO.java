package com.carlos.person_management_app.dtos;

import com.carlos.person_management_app.business.entities.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String street;
    private String zipCode;
    private String number;
    private String city;
    private String state;
    private Boolean isMain;
    private Person person;
}