package com.carlos.person_management_app.business.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Address {
    private Long id;
    private String street;
    private String zipCode;
    private String number;
    private String city;
    private String state;
    private String isMain;
}
