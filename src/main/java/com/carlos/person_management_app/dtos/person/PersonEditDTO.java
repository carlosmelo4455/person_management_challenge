package com.carlos.person_management_app.dtos.person;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonEditDTO {
    private String fullName;
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "data")
    private String birthDate;
}
