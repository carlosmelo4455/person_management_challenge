package com.carlos.person_management_app.business.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    private Long id;
    private String street;
    private String zipCode;
    private String number;
    private String city;
    private String state;
    private Boolean isMain = false;
    private Person person;
}