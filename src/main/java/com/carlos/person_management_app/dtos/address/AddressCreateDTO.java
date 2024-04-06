package com.carlos.person_management_app.dtos.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCreateDTO {
    private String street;
    private String zipCode;
    private String number;
    private String city;
    private String state;
    private Boolean isMain;
}
