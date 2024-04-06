package com.carlos.person_management_app.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressUserDTO {
    private Long id;
    private String street;
    private String zipCode;
    private String number;
    private String city;
    private String state;

}
