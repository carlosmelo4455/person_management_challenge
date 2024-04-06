package com.carlos.person_management_app.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PersonDTO {

    private Long id;
    private String fullName;
    private Date birthDate;
    private List<AddressUserDTO> addresses;
}
