package com.carlos.person_management_app.business.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Person {
    private Long id;
    private String fullName;
    private Date birthDate;
}
