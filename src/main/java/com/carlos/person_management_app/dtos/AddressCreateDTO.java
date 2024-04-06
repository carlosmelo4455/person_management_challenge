package com.carlos.person_management_app.dtos;

import lombok.Getter;

@Getter
public class AddressCreateDTO {
    private String street;
    private String zipCode;
    private String number;
    private String city;
    private String state;
    private Boolean isMain;
}
