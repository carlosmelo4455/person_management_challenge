package com.carlos.person_management_app.dtos.person;

import com.carlos.person_management_app.dtos.address.AddressUserDTO;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PersonDTO {

    private Long id;
    private String fullName;
    private String birthDate;
    private List<AddressUserDTO> addresses;

    public void setBirthDate(Date birthDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.birthDate = formatter.format(birthDate);
    }
}
