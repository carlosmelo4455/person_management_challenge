package com.carlos.person_management_app.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PersonEditDTO {
    private String fullName;
    private Date birthDate;
}
