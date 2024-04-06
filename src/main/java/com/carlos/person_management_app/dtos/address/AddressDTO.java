package com.carlos.person_management_app.dtos.address;

import com.carlos.person_management_app.dtos.person.PersonEditDTO;
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
    private PersonEditDTO person;
}